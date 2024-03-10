package qaCoursesTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    private static WebElement emailField;

    @FindBy(name = "password")
    private static WebElement passwordField;

    @FindBy(xpath = "//input[@name=\"email\"]/../..//span[contains(@class, \"text-rose-500\")]")
    private static WebElement emailFieldError;

    @FindBy(xpath = "//input[@name=\"password\"]/../..//span[contains(@class, \"text-rose-500\")]")
    private static WebElement passwordFieldError;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement submitBtn;

    @FindBy(xpath = "//p[text()='Logout']")
    private static WebElement logoutBtn;

    public LoginPage enterEmailFieldValue(String value) {
        emailField.sendKeys(value);
        return this;
    }

    public LoginPage enterPasswordFieldValue(String value) {
        passwordField.sendKeys(value);
        return this;
    }

    public String getEmailErrorValue() {
        return emailFieldError.getText();
    }

    public String getPasswordErrorValue() {
        return passwordFieldError.getText();
    }

    public boolean isSubmitBtnEnable() {
        return submitBtn.isEnabled();
    }

    public LoginPage submitForm() {
        submitBtn.click();
        return this;
    }

    public LoginPage logout() {
        logoutBtn.click();
        return this;
    }

    public LoginPage pause(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
