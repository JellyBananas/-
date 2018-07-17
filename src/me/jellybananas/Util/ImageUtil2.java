package me.jellybananas.Util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;

/**
 * Created by jellyBananas on 2018/7/14.
 */
public class ImageUtil2 {

    private static String DEFAULT_CUT_PREVFIX = "cut_";

    /**
     * Description: 根据原图与裁切size截取局部图片
     * @param srcImg 源图片
     * @param output 图片输出流
     * @param rect 需要截取部分的坐标和大小
     */
    public void cutImage(File srcImg, OutputStream output, java.awt.Rectangle rect) {
        if (srcImg.exists()) {
            java.io.FileInputStream fis = null;
            ImageInputStream iis = null;
            try {
                fis = new FileInputStream(srcImg);
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG,
                // JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames())
                        .replace("]", ",");
                String suffix = null;
                // 获取图片后缀
                if (srcImg.getName().indexOf(".") > -1) {
                    suffix = srcImg.getName().substring(srcImg.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null
                        || types.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
                    System.out.println("Sorry, the image suffix is illegal. the standard image suffix is {}."+ types);
                    return;
                }
                // 将FileInputStream 转换为ImageInputStream
                iis = ImageIO.createImageInputStream(fis);
                // 根据图片类型获取该种类型的ImageReader
                ImageReader reader = ImageIO.getImageReadersBySuffix(suffix).next();
                reader.setInput(iis, true);
                ImageReadParam param = reader.getDefaultReadParam();
                param.setSourceRegion(rect);
                BufferedImage bi = reader.read(0, param);
                ImageIO.write(bi, suffix, output);
                System.out.println("图片生成成功，请到目录下查看");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fis != null)
                        fis.close();
                    if (iis != null)
                        iis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("the src image is not exist.");
        }
    }


    //生成目标文件路径
    public void cutImage(File srcImg, String destImgPath,java.awt.Rectangle rect) {
        File destImg = new File(destImgPath);
        if (destImg.exists()) {
            String p = destImg.getPath();
            try {
                if (!destImg.isDirectory())
                    p = destImg.getParent();
                if (!p.endsWith(File.separator))
                    p = p + File.separator;
                cutImage(srcImg,new java.io.FileOutputStream(p + DEFAULT_CUT_PREVFIX+ "_"+ srcImg.getName()), rect);
            } catch (FileNotFoundException e) {
                System.out.println("the dest image is not exist.");
            }
        } else
            System.out.println("the dest image folder is not exist.");
    }

}
