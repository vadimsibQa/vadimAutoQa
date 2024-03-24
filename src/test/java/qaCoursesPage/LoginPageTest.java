package qaCoursesPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import qaCoursesTests.LoginPage;
import utils.AllureAttachmentListener;
import webDriver.Driver;

@Listeners({AllureAttachmentListener.class})
public class LoginPageTest {
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = Driver.startDriver();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void stop() {
        driver.quit();
    }

    @Test(priority = 5)
    @Description("Positive test for login with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login")
    public void loginWithValidData() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage
                .enterEmailFieldValue("vadimsib.qa@gmail.com")
                .enterPasswordFieldValue("987654321")
                .submitForm()
                .pause(1);
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/", "Login error");
    }

    @Test(priority = 4)
    @Description("Negative test for login with Invalid Password")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login")
    public void loginWithInvalidPassword() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage
                .enterEmailFieldValue("vadimsib.qa@gmail.com")
                .enterPasswordFieldValue("12345678999999")
                .submitForm()
                .pause(1);
        Assert.assertEquals(loginPage.getPasswordErrorValue(), "Email or password is not valid");
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/login");
    }

    @Test(priority = 3)
    @Description("Negative test with fields that were reversed")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login")
    public void loginWithIncorrectFields() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage
                .enterEmailFieldValue("987654321")
                .enterPasswordFieldValue("vadimsib.qa@gmail.com")
                .submitForm()
                .pause(1);
        Assert.assertFalse(loginPage.isSubmitBtnEnable());
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/login");
    }

    @Test(priority = 2)
    @Description("Negative test with empty email field")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login")
    public void loginWithEmptyEmailField() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage
                .enterPasswordFieldValue("123456789")
                .submitForm()
                .pause(1);
        Assert.assertFalse(loginPage.isSubmitBtnEnable());
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/login");
    }

    @Test(priority = 1)
    @Description("Negative test with empty password field")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Login")
    public void loginWithEmptyPasswordField() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage
                .enterEmailFieldValue("vadimsib.qa@gmail.com")
                .submitForm()
                .pause(1);
        Assert.assertFalse(loginPage.isSubmitBtnEnable());
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/login");
    }
}
