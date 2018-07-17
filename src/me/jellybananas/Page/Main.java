package me.jellybananas.Page;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");//chromedriver服务地址
        WebDriver driver = new ChromeDriver();
        driver.get("https://c.dcits.com");

        String itCode = "matengb";
        String password = "veday1142mateng1";

        WebElement itCodeInput = ((ChromeDriver) driver).findElementById("usernameInput");
        WebElement passwordInput = ((ChromeDriver) driver).findElementByName("logininput");
        WebElement codeInput = ((ChromeDriver) driver).findElementById("checkcode");
        WebElement codeImage = ((ChromeDriver) driver).findElementById("image");
        WebElement loginButton = ((ChromeDriver) driver).findElementById("loginbutton");

        itCodeInput.sendKeys(itCode);
        itCodeInput.sendKeys(Keys.TAB);
        passwordInput.sendKeys(password);
        passwordInput.sendKeys(Keys.TAB);


        itCodeInput.sendKeys(Keys.TAB);


        String code = null;
        Scanner scanner = new Scanner(System.in);
        code = scanner.nextLine();
        codeInput.sendKeys(code);
        loginButton.click();


        WebElement entry = ((ChromeDriver) driver).findElementByXPath("/html/body/div[1]/div[4]/ul[2]/li[1]/a");
        entry.click();


        WebElement add = ((ChromeDriver) driver).findElementByXPath("//*[@id=\"secondMenuDiv\"]/ul[2]/li[2]/a/span");
        add.click();

//        List<WebElement> items = ((ChromeDriver) driver).findElementsByXPath("//*[@id=\"-1\"]");
//        List<WebElement> items = ((ChromeDriver) driver).findElementsByCssSelector("#\\2d 1");
//        for (WebElement element : items) {
//            System.out.println(element.getText().toString());
//        }
        WebElement saveButton = ((ChromeDriver) driver).findElementById("save");
        for (int i = -1; i < 13; i++) {
            int index = i + 1;
            WebElement items = ((ChromeDriver) driver).findElementById(String.valueOf(i));
            String text = items.getText();
            System.out.println(text);
            if (!(text.contains("星期六") || text.contains("星期天"))) {
                WebElement itemClass = ((ChromeDriver) driver).findElementById("timeSheet[" + index + "].bgmold");
                WebElement itemHour = ((ChromeDriver) driver).findElementById("timeSheet[" + index + "].workhours");
                Select select = new Select(itemClass);
                select.selectByValue("9");
                System.out.println("value: " + itemHour.getAttribute("value"));
                if (!("8.0".equals(itemHour.getAttribute("value")))) {
                    itemHour.sendKeys(Keys.BACK_SPACE);
                    itemHour.sendKeys(Keys.BACK_SPACE);
                    itemHour.sendKeys(Keys.BACK_SPACE);
                    itemHour.sendKeys("8");
                }
            } else {
                WebElement itemClass = ((ChromeDriver) driver).findElementById("timeSheet[" + index + "].bgmold");
                WebElement itemHour = ((ChromeDriver) driver).findElementById("timeSheet[" + index + "].workhours");
                Select select = new Select(itemClass);
                select.selectByVisibleText("请选择");
                System.out.println("value: " + itemHour.getAttribute("value"));
//                if (!("8.0".equals(itemHour.getAttribute("value")))) {
//                    itemHour.sendKeys(Keys.BACK_SPACE);
//                    itemHour.sendKeys(Keys.BACK_SPACE);
//                    itemHour.sendKeys(Keys.BACK_SPACE);
//                    itemHour.sendKeys("8");
//                }
            }
            if (i == 13 - 1) {
                WebElement lastInput = ((ChromeDriver) driver).findElementByXPath("//*[@id=\"timeSheet[" + index + "].remark\"]");
                lastInput.sendKeys(Keys.TAB);
            }
        }

        saveButton.sendKeys(Keys.ENTER);


//        try {
//            BufferedImage bufferedImage = ImageIO.read(new File("pic\\checkCode.png"));
//            new ImageUtil2().cutImage(new File("pic\\checkCode.png"),
//                    new FileOutputStream(new File("pic\\checkCode2.png")),
////                    new Rectangle(295, 335, 65, 20)
//                    new Rectangle(292, 330, 72, 50)
//            );
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
