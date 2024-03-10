package refactored12lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wc3SchoolPage {
    private WebDriver driver;
    private Actions actions;
    WebDriverWait wait;

    public Wc3SchoolPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(name = "fname")
    private static WebElement firstNameInput;

    @FindBy(name = "lname")
    private static WebElement lastNameInput;

    @FindBy(xpath = "//input[@type='submit']")
    private static WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class, 'w3-panel')]")
    private static WebElement notePanel;

    // Enter values
    public Wc3SchoolPage enterFirstNameValue(String value) {
        firstNameInput.clear();
        firstNameInput.sendKeys(value);
        return this;
    }

    public Wc3SchoolPage enterLastNameValue(String value) {
        lastNameInput.clear();
        lastNameInput.sendKeys(value);
        return this;
    }

    // Methods
    public Wc3SchoolPage focusOnIframe(String id) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(id)));
        return this;
    }

    public Wc3SchoolPage submitForm() {
        submitButton.click();
        return this;
    }

    public Wc3SchoolPage outputMsgFromPanelInConsole() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'w3-panel')]")));
        System.out.println(notePanel.getText());
        return this;
    }

    public Wc3SchoolPage pause(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
