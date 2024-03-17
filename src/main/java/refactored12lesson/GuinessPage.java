package refactored12lesson;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GuinessPage {
    private WebDriver driver;
    private Actions actions;

    public GuinessPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(name = "LastName")
    private static WebElement lastNameInput;

    @FindBy(name = "FirstName")
    private static WebElement firstNameInput;

    @FindBy(name = "DateOfBirthDay")
    private static WebElement birthDayInput;

    @FindBy(name = "DateOfBirthMonth")
    private static WebElement birthMonthInput;

    @FindBy(name = "DateOfBirthYear")
    private static WebElement birthYearInput;

    @FindBy(name = "Country")
    private static WebElement countrySelect;

    @FindBy(name = "State")
    private static WebElement stateInput;

    @FindBy(name = "EmailAddress")
    private static WebElement emailInput;

    @FindBy(name = "ConfirmEmailAddress")
    private static WebElement confirmEmailInput;

    @FindBy(name = "Password")
    private static WebElement passwordInput;

    @FindBy(name = "ConfirmPassword")
    private static WebElement confirmPasswordInput;

    @FindBy(xpath = "//span[@data-valmsg-for='ConfirmPassword']")
    private static WebElement errorMsg;

    // Enter values
    public GuinessPage enterLastNameValue(String value) {
        lastNameInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterFirstNameValue(String value) {
        firstNameInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterBirthDayValue(String value) {
        birthDayInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterBirthMonthValue(String value) {
        birthMonthInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterBirthYearValue(String value) {
        birthYearInput.sendKeys(value);
        return this;
    }

    public GuinessPage selectCountryValue(String value) {
        Select dropdown = new Select(countrySelect);
        dropdown.selectByValue(value);
        return this;
    }

    public GuinessPage enterStateValue(String value) {
        stateInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterEmailValue(String value) {
        emailInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterConfirmEmailValue(String value) {
        confirmEmailInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterPasswordValue(String value) {
        passwordInput.sendKeys(value);
        return this;
    }

    public GuinessPage enterConfirmPasswordValue(String value) {
        confirmPasswordInput.sendKeys(value);
        return this;
    }

    // Methods
    public GuinessPage lostFocus() {
        actions.sendKeys(Keys.TAB).perform();
        return this;
    }

    public GuinessPage outputErrorMsgInConsole() {
        System.out.println(errorMsg.getText());
        return this;
    }
}


