package qaCoursesTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()='First name']/following-sibling::p")
    private static WebElement firstNameValue;

    @FindBy(xpath = "//p[text()='Last name']/following-sibling::p")
    private static WebElement lastNameValue;

    @FindBy(xpath = "//p[text()='Date of birth']/following-sibling::p")
    private static WebElement birthDateValue;

    @FindBy(xpath = "//a[@href='/editAccount']")
    private static WebElement editAccountBtn;

    public MainPage clickEditProfile() {
        editAccountBtn.click();
        return this;
    }

    public String getFirstName() {
        return firstNameValue.getText();
    }

    public String getLastName() {
        return lastNameValue.getText();
    }

    public String getBirthDate() {
        return birthDateValue.getText();
    }

    public MainPage pause(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
