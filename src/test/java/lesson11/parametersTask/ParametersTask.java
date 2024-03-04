package lesson11.parametersTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import WebDriver.Driver;
import org.testng.annotations.*;

import java.time.Duration;

public class ParametersTask {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.createDriver();
    }

    @Parameters({"user"})
    @Test
    public void testLogin(String user) {
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));

        switch (user) {
            case "user1" -> {
                driver.findElement(By.name("email")).sendKeys("vadimsib001@gmail.com");
                driver.findElement(By.name("password")).sendKeys("123456789");
            }
            case "user2" -> {
                driver.findElement(By.name("email")).sendKeys("vadimsib002@gmail.com");
                driver.findElement(By.name("password")).sendKeys("123456789");
            }
            case "user3" -> {
                driver.findElement(By.name("email")).sendKeys("vadimsib003@gmail.com");
                driver.findElement(By.name("password")).sendKeys("123456789");
            }
        }

        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text() = 'My account']")));
        By header = (By.tagName("h1"));
        Assert.assertEquals(driver.findElement(header).getText(), "My account");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
