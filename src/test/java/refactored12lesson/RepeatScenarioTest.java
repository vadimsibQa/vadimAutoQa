package refactored12lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CustomTestListener;
import webDriver.Driver;

import java.util.Set;

@Listeners({CustomTestListener.class})
public class RepeatScenarioTest {
    private static WebDriver driver;
    private GooglePage googlePage;
    private Wc3SchoolPage wc3SchoolPage;
    private GuinessPage guinessPage;
    private AlertsPage alertsPage;

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

    @BeforeClass
    public void setUp() {
        driver = Driver.startDriver();
        googlePage = new GooglePage(driver);
        wc3SchoolPage = new Wc3SchoolPage(driver);
        guinessPage = new GuinessPage(driver);
        alertsPage = new AlertsPage(driver);
    }

    @AfterClass
    public void stop() {
        googlePage.pause(5);
        driver.quit();
    }

    @Test
    public void repeatScenario() {
        // 1) открыть поиск гугл: https://www.google.com/search
        driver.get("https://www.google.com/search");

        // 2) После ввести в поисковую строку следующую ссылку: https://www.guinnessworldrecords.com/account/register?
        googlePage
                .enterSearchValue("https://www.guinnessworldrecords.com/account/register?")
                .submitSearch()
                .openGoogleLinkInNewWindow(By.xpath("//a[@href = 'https://www.guinnessworldrecords.com/account/register']"));

        // 3) После ввести в поисковую строку следующую ссылку: https://www.hyrtutorials.com/p/alertsdemo.html
        // Открыть подходящую ссылку в новом окне, которая будет отображаться в результатах.
        googlePage
                .clearSearchValue()
                .enterSearchValue("\"https://www.hyrtutorials.com/p/alertsdemo.html\"")
                .submitSearch()
                .openGoogleLinkInNewWindow(By.xpath("//a[@href = 'https://www.hyrtutorials.com/p/alertsdemo.html']"));

        // 4) Открыть в активном окне следующую ссылку: https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");

        // В открытом окне заполнить поля своим именем и фамилией и нажать кнопку ‘Submit’.
        // После вывести в консоль текст данного элемента:
        wc3SchoolPage
                .focusOnIframe("iframeResult")
                .enterFirstNameValue("Vadim")
                .enterLastNameValue("Sibgatullin")
                .submitForm()
                .outputMsgFromPanelInConsole();

        // 5) Переключиться на окно, в котором открыта следующая ссылка: https://www.guinnessworldrecords.com/account/register?
        switchToTabByUrl(driver, "https://www.guinnessworldrecords.com/account/register");
        guinessPage
                .enterLastNameValue("Sibgatullin")
                .enterFirstNameValue("Vadim")
                .enterBirthDayValue("03")
                .enterBirthMonthValue("06")
                .enterBirthYearValue("1991")
                .selectCountryValue("RU")
                .enterStateValue("Bashkortostan")
                .enterEmailValue("vadimsib.qa@gmail.com")
                .enterConfirmEmailValue("vadimsib.qa@gmail.com")
                .enterPasswordValue("1234567")
                .enterConfirmPasswordValue("4567890")
                .lostFocus()
                .outputErrorMsgInConsole();

        // 6) Переключиться на окно, в котором открыта следующая ссылка: https://www.hyrtutorials.com/p/alertsdemo.html
        switchToTabByUrl(driver, "https://www.hyrtutorials.com/p/alertsdemo.html");

        alertsPage
                .clickAlertBtn()
                .acceptAlert()
                .clickConfirmBtn()
                .dismissAlert()
                .clickPromptBtn()
                .enterPromptAlert("\"Final step of this task\"")
                .outputMsgInConsole();
    }
}
