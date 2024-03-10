package refactored12lesson;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsPage {
    private WebDriver driver;
    private Actions actions;

    public AlertsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//button[@onclick='alertFunction()']")
    private static WebElement alertBtn;

    @FindBy(xpath = "//button[@onclick='confirmFunction()']")
    private static WebElement confirmBtn;

    @FindBy(xpath = "//button[@onclick='promptFunction()']")
    private static WebElement promptBtn;

    @FindBy(id = "output")
    private static WebElement outputElement;

    // Clicks
    public AlertsPage clickAlertBtn() {
        alertBtn.click();
        return this;
    }

    public AlertsPage clickConfirmBtn() {
        confirmBtn.click();
        return this;
    }

    public AlertsPage clickPromptBtn() {
        promptBtn.click();
        return this;
    }

    // Methods
    public AlertsPage acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public AlertsPage dismissAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        return this;
    }

    public AlertsPage enterPromptAlert(String text) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(text);
        alert.accept();
        return this;
    }

    public AlertsPage outputMsgInConsole() {
        System.out.println(outputElement.getText());
        return this;
    }
}
