package qaCoursesPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import qaCoursesTests.RegisterPage;
import utils.AllureAttachmentListener;
import webDriver.Driver;

@Listeners({AllureAttachmentListener.class})
public class RegisterPageTest {
    private static WebDriver driver;
    private RegisterPage registerPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.startDriver();
        registerPage = new RegisterPage(driver);
        driver.get("https://qa-course-01.andersenlab.com/registration");
    }

    @AfterClass
    public void stop() {
        driver.quit();
    }

    @Test(priority = 4)
    @Description("Positive test register with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Registration")
    public void registerWithValidData() {
        registerPage
                .enterFirstNameValue("Vadim")
                .enterLastNameValue("Sibgatullin")
                .enterBirthDateValue("06/06/2000")
                .clickOnTitle()
                .enterEmailValue("vadimsib.qa6@gmail.com")
                .enterPasswordValue("123456789")
                .enterConfirmPasswordValue("123456789")
                .submitForm()
                .pause(1);

        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/");
    }

    @Test(priority = 3)
    @Description("Negative register test with invalid email")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Registration")
    public void registerWithInvalidEmailData() {
        registerPage
                .enterFirstNameValue("Vadim")
                .enterLastNameValue("Sibgatullin")
                .enterBirthDateValue("06/06/2000")
                .clickOnTitle()
                .enterEmailValue("vadimsib.qa4")
                .enterPasswordValue("123456789")
                .enterConfirmPasswordValue("123456789")
                .submitForm();

        Assert.assertEquals(registerPage.getEmailErrorValue(), "Invalid email address", "Wrong error-msg component");
        Assert.assertFalse(registerPage.isSubmitBtnEnable(), "Submit button is enabled");

        registerPage.clearAllFields();
    }

    @Test(priority = 2)
    @Description("Negative register test with invalid password")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Registration")
    public void registerWithInvalidPasswordData() {
        registerPage
                .enterFirstNameValue("Vadim")
                .enterLastNameValue("Sibgatullin")
                .enterBirthDateValue("06/06/2000")
                .clickOnTitle()
                .enterEmailValue("vadimsib.qa4@gmail.com")
                .enterPasswordValue("123")
                .enterConfirmPasswordValue("123");

        Assert.assertEquals(registerPage.getPasswordFieldErrorValue(), "Minimum 8 characters", "Wrong error-msg component");
        Assert.assertFalse(registerPage.isSubmitBtnEnable(), "Submit button is enabled");

        registerPage.clearAllFields();

    }

    @Test(priority = 1)
    @Description("Negative register test with invalid confirm password")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Registration")
    public void registerWithInvalidConfirmPasswordData() {
        registerPage
                .enterFirstNameValue("Vadim")
                .enterLastNameValue("Sibgatullin")
                .enterBirthDateValue("06/06/2000")
                .clickOnTitle()
                .enterEmailValue("vadimsib.qa4@gmail.com")
                .enterPasswordValue("123456789")
                .enterConfirmPasswordValue("987654321")
                .clickOnTitle();

        Assert.assertEquals(registerPage.getRepeatPasswordFieldErrorValue(), "Passwords must match", "Wrong error-msg component");
        Assert.assertFalse(registerPage.isSubmitBtnEnable(), "Submit button is enabled");

        registerPage.clearAllFields();
    }
}
