package lesson12.registerForm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import WebDriver.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;


public class RegisterForm {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    public WebElement pickElement(By selector) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.createDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void scrollPractice() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        pickElement(By.name("firstName")).sendKeys("Sam");
        pickElement(By.name("lastName")).sendKeys("Meller");

        driver.findElement(By.name("dateOfBirth")).click();
        new Select(pickElement(By.xpath("//div[@class='react-datepicker']//select[1]"))).selectByValue("1990");
        new Select(pickElement(By.xpath("//div[@class='react-datepicker']//select[2]"))).selectByValue("August");
        pickElement(By.xpath("//div[contains(@class, 'react-datepicker__day--010')]")).click();
        // fix to close date picked modal
        pickElement(By.xpath("//h1[text()='Registration']")).click();

        pickElement(By.name("email")).sendKeys("sam@sam.com");
        pickElement(By.name("password")).sendKeys("123456789");
        pickElement(By.name("passwordConfirmation")).sendKeys("123456789");

        WebElement submitBtn = pickElement(By.xpath("//button[@type='submit']"));
        Assert.assertTrue(submitBtn.isEnabled(), "Button is not enabled");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
