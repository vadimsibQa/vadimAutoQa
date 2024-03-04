package lesson11.dataProvider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import WebDriver.Driver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DataProviderTask {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.createDriver();
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String login, String password) {
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("email")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text() = 'My account']")));
        By header = (By.tagName("h1"));
        Assert.assertEquals(driver.findElement(header).getText(), "My account");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider(name = "loginData")
    public Object[][] createData() {
        return new Object[][]{
                {"vadimsib001@gmail.com", "123456789"}, {"vadimsib002@gmail.com", "123456789"}, {"vadimsib003@gmail.com",
                "123456789"},
        };
    }
}
