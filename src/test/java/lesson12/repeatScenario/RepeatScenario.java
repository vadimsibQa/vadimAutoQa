package lesson12.repeatScenario;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import WebDriver.Driver;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Set;

public class RepeatScenario {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    public void switchToTabByUrl(WebDriver driver, String url) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            String tabUrl = driver.getCurrentUrl();
            if (tabUrl.equals(url)) {
                break;
            }
        }
    }

    public WebElement pickElement(By selector) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public void openGoogleLinkInNewWindow(By selector) {
        WebElement googleSearchLink = pickElement(selector);
        actions
                .keyDown(Keys.CONTROL)
                .click(googleSearchLink)
                .keyUp(Keys.CONTROL)
                .build().perform();
    }

    public void clearFieldAndEnterValue(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = Driver.createDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void repeatScenario() {
        // 1) открыть поиск гугл: https://www.google.com/search
        driver.get("https://www.google.com/search");

        // 2) После ввести в поисковую строку следующую ссылку: https://www.guinnessworldrecords.com/account/register?
        WebElement googleSearchInput = pickElement(By.xpath("//textarea[@name='q']"));
        googleSearchInput.sendKeys("https://www.guinnessworldrecords.com/account/register?");
        actions.sendKeys(Keys.ENTER).perform();

        // Открыть подходящую ссылку в новом окне, которая будет отображаться в результатах.
        openGoogleLinkInNewWindow(By.xpath("//a[@href = 'https://www.guinnessworldrecords.com/account/register']"));

        // 3) После ввести в поисковую строку следующую ссылку: https://www.hyrtutorials.com/p/alertsdemo.html
        googleSearchInput = pickElement(By.xpath("//textarea[@name='q']"));
        googleSearchInput.clear();
        googleSearchInput.sendKeys("https://www.hyrtutorials.com/p/alertsdemo.html");
        actions.sendKeys(Keys.ENTER).perform();

        // Открыть подходящую ссылку в новом окне, которая будет отображаться в результатах.
        openGoogleLinkInNewWindow(By.xpath("//a[@href = 'https://www.hyrtutorials.com/p/alertsdemo.html']"));

        // 4) Открыть в активном окне следующую ссылку: https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");

        // В открытом окне заполнить поля своим именем и фамилией и нажать кнопку ‘Submit’.
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframeResult")));
        WebElement nameInput = pickElement(By.name("fname"));
        WebElement lastnameInput = pickElement(By.name("lname"));
        WebElement submitBtn = pickElement(By.xpath("//input[@type='submit']"));
        clearFieldAndEnterValue(nameInput, "Vadim");
        clearFieldAndEnterValue(lastnameInput, "Sibgatullin");
        submitBtn.click();

        // После вывести в консоль текст данного элемента:
        WebElement notePanel = pickElement(By.xpath("//div[contains(@class, \"w3-panel\")]"));
        System.out.println(notePanel.getText());

        // 5) Переключиться на окно, в котором открыта следующая ссылка: https://www.guinnessworldrecords.com/account/register?
        switchToTabByUrl(driver, "https://www.guinnessworldrecords.com/account/register");
        pickElement(By.name("LastName")).sendKeys("Sibgatullin");
        pickElement(By.name("FirstName")).sendKeys("Vadim");
        pickElement(By.name("DateOfBirthDay")).sendKeys("03");
        pickElement(By.name("DateOfBirthMonth")).sendKeys("06");
        pickElement(By.name("DateOfBirthYear")).sendKeys("1991");
        new Select(pickElement(By.name("Country"))).selectByValue("RU");
        pickElement(By.name("State")).sendKeys("Republic of Bashkortostan");
        pickElement(By.name("EmailAddress")).sendKeys("vadimsib.qa@gmail.com");
        pickElement(By.name("ConfirmEmailAddress")).sendKeys("vadimsib.qa@gmail.com");
        pickElement(By.name("Password")).sendKeys("1234567");
        pickElement(By.name("ConfirmPassword")).sendKeys("4567890");
        actions.sendKeys(Keys.TAB).perform();
        WebElement errorMsg = pickElement(By.xpath("//span[@data-valmsg-for=\"ConfirmPassword\"]"));
        System.out.println(errorMsg.getText());

        // 6) Переключиться на окно, в котором открыта следующая ссылка: https://www.hyrtutorials.com/p/alertsdemo.html
        switchToTabByUrl(driver, "https://www.hyrtutorials.com/p/alertsdemo.html");
        WebElement alertBtn = pickElement(By.xpath("//button[@onclick='alertFunction()']"));
        WebElement confirmBtn = pickElement(By.xpath("//button[@onclick='confirmFunction()']"));
        WebElement promptBtn = pickElement(By.xpath("//button[@onclick='promptFunction()']"));
        alertBtn.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        confirmBtn.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        promptBtn.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = "Final step of this task";
        alert.sendKeys(text);
        alert.accept();
        WebElement outputValue = pickElement(By.id("output"));
        System.out.println(outputValue.getText());
    }

    @BeforeMethod
    public void afterMethod() {
        driver.quit();
    }
}
