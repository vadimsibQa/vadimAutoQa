package Lesson10;

import WebDriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OpenWindows {
    public static void main(String[] args) {
        WebDriver driver = Driver.createDriver();
        driver.get("http://www.automationpractice.pl/index.php");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://zoo.waw.pl/");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.w3schools.com/");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.clickspeedtester.com/click-counter/");

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(" https://andersenlab.com/");

        System.out.println(driver.getWindowHandles());

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            String pageTitle = driver.getTitle();
            String pageUrl = driver.getCurrentUrl();
            System.out.println(pageTitle);
            System.out.println(pageUrl);
            if (pageTitle.contains("Zoo")) {
                driver.close();
                System.out.println("It's Zoooo!");
            }
        }


    }
}
