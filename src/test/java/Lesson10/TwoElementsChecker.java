package Lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import WebDriver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TwoElementsChecker {
    public static void main(String[] args) {
        WebDriver driver = Driver.createDriver();
        driver.get("https://qa-course-01.andersenlab.com/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        WebElement element1 = driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement element2 = driver.findElement(By.xpath("//a[@href='/registration']"));
        TwoElementsChecker.compareElements(element1, element2);

    }

    public static void compareElements(WebElement element1, WebElement element2) {
        int element1Top = element1.getLocation().getY();
        int element1Left = element1.getLocation().getX();
        int element2Top = element2.getLocation().getY();
        int element2Left = element2.getLocation().getX();

        if (element1Top < element2Top) {
            System.out.println("Первый элемент располагается выше на странице");
        } else if (element1Top > element2Top) {
            System.out.println("Второй элемент располагается выше на странице");
        } else {
            System.out.println("Элементы находятся на одном уровне по оси Y");
        }

        if (element1Left < element2Left) {
            System.out.println("Первый элемент располагается левее на странице");
        } else if (element1Left > element2Left) {
            System.out.println("Второй элемент располагается левее на странице");
        } else {
            System.out.println("Элементы находятся на одгом уровне по оси X");
        }

        int element1Area = element1.getSize().getWidth() * element1.getSize().getHeight();
        int element2Area = element2.getSize().getWidth() * element2.getSize().getHeight();

        if (element1Area > element2Area) {
            System.out.println("Первый элемент имеет большую площадь");
        } else if (element1Area < element2Area) {
            System.out.println("Второй элемент имеет большую площадь");
        } else {
            System.out.println("Элементы имеют одинаковую площадь");
        }
    }
}
