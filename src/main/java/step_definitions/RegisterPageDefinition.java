package step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import webDriver.Driver;

import java.time.Duration;

public class RegisterPageDefinition {

    private WebDriver driver;
    private WebDriverWait wait;

    @After
    public void closeSeleniumDriver() {
        driver.quit();
    }


    @Given("Set up driver")
    public void SetUpDriver() {
        this.driver = Driver.startDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    private static class formValues {
        private static final String url = "https://qa-course-01.andersenlab.com/registration";
        private static final String firstName = "Vadim";
        private static final String lastName = "Sibgatullin";
        private static final String birthdate = "06/06/2000";
        private static final String email = "vadimsib.qa6@gmail.com";
        private static final String password = "123456789";
        private static final String repeatPassword = "123456789";
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

    public String getEmailErrorValue() {
        return emailFieldError.getText();
    }

    public String getPasswordFieldErrorValue() {
        return passwordFieldError.getText();
    }


    //BDD
    @Given("User is on Register Page")
    public void user_is_on_register_page() {
        driver.get(formValues.url);
    }
    @When("I enter first name {string}")
    public void i_enter_first_name(String value) {
        firstNameInput.sendKeys(value);
    }
    @And("I enter last name {string}")
    public void i_enter_last_name(String value) {
        lastNameInput.sendKeys(value);
    }
    @And("I enter birthdate {string}")
    public void i_enter_birthdate(String value) {
        dateOfBirthInput.sendKeys(value);
        formTitle.click();
    }
    @And("I enter email {string}")
    public void i_enter_email(String value) {
        emailInput.sendKeys(value);
    }
    @And("I enter password {string}")
    public void i_enter_password(String value) {
        passwordInput.sendKeys(value);
    }
    @And("I enter confirm password {string}")
    public void i_enter_confirm_password(String value) {
        passwordConfirmInput.sendKeys(value);
    }
    @And("Submit the form")
    public void submit_the_form() {
        submitBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("I was redirected on main page")
    public void i_was_redirected() {
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/");
    }

    @Then("I can see email field error")
    public void i_see_email_error() {
        Assert.assertEquals(getEmailErrorValue(), "Invalid email address", "Wrong error-msg component");
    }

    @Then("I can see password field error")
    public void i_see_password_error() {
        Assert.assertEquals(getPasswordFieldErrorValue(), "Minimum 8 characters", "Wrong error-msg component");
    }
}
