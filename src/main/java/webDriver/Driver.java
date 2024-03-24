package webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
    private static WebDriver driver;

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getInstance() {
        if (driver == null) {
            try {
                driver = createDriver();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return driver;
    }

    public static WebDriver startDriver() {
        driver = getInstance();
        return driver;
    }
}