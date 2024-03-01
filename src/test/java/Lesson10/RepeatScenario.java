package Lesson10;

import WebDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RepeatScenario {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = Driver.createDriver();
        driver.get("http://www.automationpractice.pl/index.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // search first good
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        driver.findElement(By.id("search_query_top")).sendKeys("Printed chiffon dress");
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();

        // sort by list
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='List']"))).click();

        // search container-row element with the good
        WebElement requiredProductContainer = null;

        List<WebElement> productContainers = driver.findElements(By.className("product-container"));
        for (WebElement productContainer : productContainers) {
            WebElement link = productContainer.findElement(By.xpath("//a[contains(text(), 'Printed Chiffon Dress')][@title='Printed Chiffon Dress']"));
            if (link != null) {
                requiredProductContainer = productContainer;
                break;
            }
        }

        // click on Add to compare button
        if (requiredProductContainer != null) {
            WebElement compareLink = requiredProductContainer.findElement(By.xpath("//a[contains(text(), 'Add " +
                    "to Compare')]"));
            compareLink.click();
        }

        // Fix compare button click issue
        Thread.sleep(3000);

        // move to Women page
        driver.findElement(By.xpath("//a[@title='Women']")).click();

        // search second good
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));
        driver.findElement(By.id("search_query_top")).sendKeys("Faded short");
        driver.findElement(By.xpath("//button[@name='submit_search']")).click();

        // search container-row element with the good
        WebElement requiredProductContainerSecond = null;

        List<WebElement> productContainersTwo = driver.findElements(By.className("product-container"));
        for (WebElement productContainer: productContainersTwo) {
            WebElement link = productContainer.findElement(By.xpath("//a[contains(text(), 'Faded Short')][contains" +
                    "(@title, 'Faded Short')]"));
            if (link != null) {
                requiredProductContainerSecond = productContainer;
                break;
            }
        }

        // click on Add to compare button
        if (requiredProductContainerSecond != null) {
            WebElement compareLink = requiredProductContainerSecond.findElement(By.xpath("//a[contains(text(), 'Add " +
                    "to Compare')]"));
            compareLink.click();
        }

        driver.findElement(By.xpath("//button[contains(@class, 'bt_compare')]")).click();
    }
}
