package qaCoursesTests;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditUserPage {
    private WebDriver driver;

    public EditUserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//h1[text()='Edit account']")
    private static WebElement formTitle;

    @FindBy(name = "firstName")
    private static WebElement firstNameInput;

    @FindBy(name = "lastName")
    private static WebElement lastNameInput;

    @FindBy(name = "dateOfBirth")
    private static WebElement dateOfBirthInput;

    @FindBy(name = "password")
    private static WebElement passwordInput;

    @FindBy(name = "passwordConfirmation")
    private static WebElement passwordConfirmInput;

    @FindBy(xpath = "//input[@name='firstName']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement firstNameFieldError;

    @FindBy(xpath = "//input[@name='lastName']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement lastNameFieldError;

    @FindBy(xpath = "//input[@name='password']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement passwordFieldError;

    @FindBy(xpath = "//input[@name='passwordConfirmation']/../..//span[contains(@class, 'text-rose-500')]")
    private static WebElement repeatPasswordFieldError;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement submitBtn;

    // Get Values
    public String getFirstNameErrorValue() {
        return firstNameFieldError.getText();
    }

    public String getLastNameErrorValue() {
        return lastNameFieldError.getText();
    }

    public String getPasswordFieldErrorValue() {
        return passwordFieldError.getText();
    }

    public String getRepeatPasswordFieldErrorValue() {
        return repeatPasswordFieldError.getText();
    }


    // Enter Values
    public EditUserPage enterFirstNameValue(String value) {
        firstNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        firstNameInput.sendKeys(value);
        return this;
    }

    public EditUserPage enterLastNameValue(String value) {
        lastNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        lastNameInput.sendKeys(value);
        return this;
    }

    public EditUserPage enterDateOfBirthValue(String value) {
        dateOfBirthInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        dateOfBirthInput.sendKeys(value);
        return this;
    }

    public EditUserPage enterPasswordValue(String value) {
        passwordInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        passwordInput.sendKeys(value);
        return this;
    }

    public EditUserPage enterRepeatPasswordValue(String value) {
        passwordConfirmInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        passwordConfirmInput.sendKeys(value);
        return this;
    }

    // Check states
    public boolean isSubmitBtnEnable() {
        return submitBtn.isEnabled();
    }

    // Clicks
    public EditUserPage clickOnTitle() {
        formTitle.click();
        return this;
    }

    public EditUserPage submitForm() {
        submitBtn.click();
        return this;
    }

    // Pause Method
    public EditUserPage pause(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
