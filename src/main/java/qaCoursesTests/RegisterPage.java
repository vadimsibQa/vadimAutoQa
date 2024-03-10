package qaCoursesTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//h1[text()='Registration']")
    private static WebElement formTitle;

    @FindBy(name = "firstName")
    private static WebElement firstNameInput;

    @FindBy(name = "lastName")
    private static WebElement lastNameInput;

    @FindBy(name = "dateOfBirth")
    private static WebElement dateOfBirthInput;

    @FindBy(name = "email")
    private static WebElement emailInput;

    @FindBy(name = "password")
    private static WebElement passwordInput;

    @FindBy(name = "passwordConfirmation")
    private static WebElement passwordConfirmInput;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement submitBtn;

    @FindBy(xpath = "//input[@name='email']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement emailFieldError;

    @FindBy(xpath = "//input[@name='password']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement passwordFieldError;

    @FindBy(xpath = "//input[@name='passwordConfirmation']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement repeatPasswordFieldError;

    // Get Values
    public String getEmailErrorValue() {
        return emailFieldError.getText();
    }

    public String getPasswordFieldErrorValue() {
        return passwordFieldError.getText();
    }

    public String getRepeatPasswordFieldErrorValue() {
        return repeatPasswordFieldError.getText();
    }

    // Enter Values
    public RegisterPage enterFirstNameValue(String value) {
        firstNameInput.sendKeys(value);
        return this;
    }

    public RegisterPage enterLastNameValue(String value) {
        lastNameInput.sendKeys(value);
        return this;
    }

    public RegisterPage enterBirthDateValue(String value) {
        dateOfBirthInput.sendKeys(value);
        return this;
    }

    public RegisterPage enterEmailValue(String value) {
        emailInput.sendKeys(value);
        return this;
    }

    public RegisterPage enterPasswordValue(String value) {
        passwordInput.sendKeys(value);
        return this;
    }

    public RegisterPage enterConfirmPasswordValue(String value) {
        passwordConfirmInput.sendKeys(value);
        return this;
    }

    //Clear fields
    public RegisterPage clearAllFields() {
        firstNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        lastNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        dateOfBirthInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        emailInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        passwordInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        passwordConfirmInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        return this;
    }

    // Check states
    public boolean isSubmitBtnEnable() {
        return submitBtn.isEnabled();
    }

    // Clicks
    public RegisterPage clickOnTitle() {
        formTitle.click();
        return this;
    }

    public RegisterPage submitForm() {
        submitBtn.click();
        return this;
    }

    // Pause Method
    public RegisterPage pause(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
