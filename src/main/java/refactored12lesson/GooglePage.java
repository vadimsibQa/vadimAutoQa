package refactored12lesson;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GooglePage {
    private WebDriver driver;
    private Actions actions;
    WebDriverWait wait;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Elements
    @FindBy(xpath = "//textarea[@name='q']")
    private static WebElement searchField;


    // Enter values
    public GooglePage enterSearchValue(String value) {
        searchField.sendKeys(value);
        return this;
    }

    // Methods
    public GooglePage clearSearchValue() {
        searchField.clear();
        return this;
    }

    public GooglePage submitSearch() {
        actions.sendKeys(Keys.ENTER).perform();
        return this;
    }

    public void openGoogleLinkInNewWindow(By selector) {
        WebElement googleSearchLink =  wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        actions
                .keyDown(Keys.CONTROL)
                .click(googleSearchLink)
                .keyUp(Keys.CONTROL)
                .build().perform();
    }

    public GooglePage pause(int seconds) {
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
