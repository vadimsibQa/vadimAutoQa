package qaCoursesPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import qaCoursesTests.LoginPage;
import utils.CustomTestListener;
import webDriver.Driver;

@Listeners({CustomTestListener.class})
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
