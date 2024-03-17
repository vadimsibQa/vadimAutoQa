package qaCoursesPage;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import qaCoursesTests.EditUserPage;
import qaCoursesTests.LoginPage;
import qaCoursesTests.MainPage;
import utils.CustomTestListener;
import webDriver.Driver;

@Listeners({CustomTestListener.class})
public class EditUserPageTest  {
    private static WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private EditUserPage editUserPage;


    @BeforeClass
    public void setUp() {
        driver = Driver.startDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        editUserPage = new EditUserPage(driver);
        loginWithValidData();
    }

    @AfterClass
    public void stop() {
        driver.quit();
    }

    public void loginWithValidData() {
        driver.get("https://qa-course-01.andersenlab.com/login");
        loginPage
                .enterEmailFieldValue("vadimsib.qa2@gmail.com")
                .enterPasswordFieldValue("123456789")
                .submitForm()
                .pause(3);
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa-course-01.andersenlab.com/", "Login error");
    }

    @Test(priority = 6)
    public void returnDefaultValues() {
        mainPage
                .clickEditProfile();
        editUserPage
                .enterFirstNameValue("Vadim")
                .enterLastNameValue("Sibgatullin")
                .enterDateOfBirthValue("03/03/2000")
                .clickOnTitle()
                .enterPasswordValue("123456789")
                .enterRepeatPasswordValue("123456789")
                .submitForm()
                .pause(1);
        Assert.assertEquals(mainPage.getFirstName(), "Vadim", "Wrong First Name");
        Assert.assertEquals(mainPage.getLastName(), "Sibgatullin", "Wrong Last Name");
        Assert.assertEquals(mainPage.getBirthDate(), "03/03/2000", "Wrong Birth Date");
    }

    @Test(priority = 5)
    public void editPersonalInfo() {
        mainPage
                .clickEditProfile();
        editUserPage
                .enterFirstNameValue("Vadim02")
                .enterLastNameValue("Sibgatullin02")
                .enterDateOfBirthValue("06/06/2004")
                .clickOnTitle()
                .submitForm()
                .pause(1);
        Assert.assertEquals(mainPage.getFirstName(), "Vadim02", "Wrong First Name");
        Assert.assertEquals(mainPage.getLastName(), "Sibgatullin02", "Wrong Last Name");
        Assert.assertEquals(mainPage.getBirthDate(), "06/06/2004", "Wrong Birth Date");
    }

    @Test(priority = 4)
    public void editPersonalInfoAndPassword() {
        editUserPage
                .enterFirstNameValue("Vadim03")
                .enterLastNameValue("Sibgatullin03")
                .enterDateOfBirthValue("06/06/2006")
                .clickOnTitle()
                .enterPasswordValue("987654321")
                .enterRepeatPasswordValue("987654321")
                .submitForm()
                .pause(1);
        Assert.assertEquals(mainPage.getFirstName(), "Vadim03", "Wrong First Name");
        Assert.assertEquals(mainPage.getLastName(), "Sibgatullin03", "Wrong Last Name");
        Assert.assertEquals(mainPage.getBirthDate(), "06/06/2006", "Wrong Birth Date");
    }

    @Test(priority = 3)
    public void editPersonalInfoWithoutFirstName() {
        editUserPage
                .enterFirstNameValue("")
                .enterLastNameValue("Sibgatullin03")
                .enterDateOfBirthValue("06/06/2006")
                .clickOnTitle()
                .enterPasswordValue("987654321")
                .enterRepeatPasswordValue("987654321")
                .pause(1);
        Assert.assertEquals(editUserPage.getFirstNameErrorValue(), "Required", "Wrong error-msg component");
        Assert.assertFalse(editUserPage.isSubmitBtnEnable(), "Submit button is enabled");
    }

    @Test(priority = 2)
    public void editPersonalInfoWithInvalidPassword() {
        editUserPage
                .enterFirstNameValue("Vadim03")
                .enterLastNameValue("Sibgatullin03")
                .enterDateOfBirthValue("06/06/2006")
                .clickOnTitle()
                .enterPasswordValue("123")
                .enterRepeatPasswordValue("123")
                .pause(1);
        Assert.assertEquals(editUserPage.getPasswordFieldErrorValue(), "Minimum 8 characters", "Wrong error-msg component");
        Assert.assertFalse(editUserPage.isSubmitBtnEnable(), "Submit button is enabled");
    }

    @Test(priority = 1)
    public void editPersonalInfoWithInvalidRepeatPassword() {
        mainPage
                .clickEditProfile();
        editUserPage
                .enterFirstNameValue("Vadim03")
                .enterLastNameValue("Sibgatullin03")
                .enterDateOfBirthValue("06/06/2006")
                .clickOnTitle()
                .enterPasswordValue("123456789")
                .enterRepeatPasswordValue("987654321")
                .clickOnTitle()
                .pause(1);
        Assert.assertEquals(editUserPage.getRepeatPasswordFieldErrorValue(), "Passwords must match", "Wrong error-msg component");
        Assert.assertFalse(editUserPage.isSubmitBtnEnable(), "Submit button is enabled");
    }
}
