package lesson12.actionsAndersenLab;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import WebDriver.Driver;
import org.testng.annotations.Test;
import java.time.Duration;

public class ActionsAndersenLab {
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

    // Практика на использование Actions
    @Test
    public void scrollPractice() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://andersenlab.com/");
        WebElement contactUsForm = pickElement(By.xpath("//div[contains(@class, 'ContactUs-module--wrapper')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", contactUsForm);
        WebElement contactUsFormSubmitBtn = pickElement(By.xpath("//div[contains(@class, 'ContactUs-module--wrapper')]//button[@type='submit']"));
        contactUsFormSubmitBtn.click();
        WebElement errorField = pickElement(By.xpath("//div[contains(@class, 'FormError-module')]"));
        Assert.assertTrue(errorField.getText().equals("Please, select the industry"), "Error element in not displaying");
    }

    @Test
    public void hoverPractice() throws InterruptedException {
        driver.get("https://andersenlab.com/");
        WebElement link = pickElement(By.xpath("//a[@href = '/services']"));
        actions.moveToElement(link).perform();
        WebElement panelTitle = pickElement(By.xpath("//p[contains(@class, 'SubMenu-module')][text()='Services']"));
        Assert.assertTrue(panelTitle.isDisplayed(), "Title is not displaying");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}
