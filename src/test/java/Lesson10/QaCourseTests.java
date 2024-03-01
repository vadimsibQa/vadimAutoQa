package Lesson10;

import WebDriver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//1)Автоматизируйте по два тест-кейса из каждого модуля, которые вы писали для предыдущего домашнего задания.
public class QaCourseTests {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = Driver.createDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Test Case TU01
        driver.get("https://qa-course-01.andersenlab.com/registration");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("firstName")).sendKeys("Vadim");
        driver.findElement(By.name("lastName")).sendKeys("Sibgatullin");
        driver.findElement(By.name("dateOfBirth")).sendKeys("06/06/2006");
        driver.findElement(By.name("email")).sendKeys("vadimsib.qa13@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("123456789");
        // fix to close date picked modal
        driver.findElement(By.xpath("//h1[text()='Registration']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Test Case TU02
        driver.get("https://qa-course-01.andersenlab.com/registration");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("firstName")).sendKeys("Vadim");
        driver.findElement(By.name("lastName")).sendKeys("Sibgatullin");
        driver.findElement(By.name("dateOfBirth")).sendKeys("06/06/2006");
        driver.findElement(By.name("email")).sendKeys("vadimsib");
        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.name("passwordConfirmation")).sendKeys("123456789");
        // fix to close date picked modal
        driver.findElement(By.xpath("//h1[text()='Registration']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Test Case TU06
        driver.get("https://qa-course-01.andersenlab.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("email")).sendKeys("vadimsib.qa13@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Test Case TU07
        driver.get("https://qa-course-01.andersenlab.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("email")).sendKeys("vadimsib.qa13@gmail.com");
        driver.findElement(By.name("password")).sendKeys("88888888");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Test Case TU11
        driver.get("https://qa-course-01.andersenlab.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("email")).sendKeys("vadimsib.qa13@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/editAccount']"))).click();
        driver.findElement(By.name("firstName")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("lastName")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("dateOfBirth")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("firstName")).sendKeys("Vadim02");
        driver.findElement(By.name("lastName")).sendKeys("Sibgatullin02");
        driver.findElement(By.name("dateOfBirth")).sendKeys("07/07/2006");
        // fix to close date picked modal
        driver.findElement(By.xpath("//h1[text()='Edit account']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Test Case TU13
        driver.get("https://qa-course-01.andersenlab.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("email")).sendKeys("vadimsib.qa13@gmail.com");
        driver.findElement(By.name("password")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/editAccount']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(By.name("firstName")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("lastName")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("lastName")).sendKeys("Sibgatullin03");
        driver.findElement(By.name("dateOfBirth")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.findElement(By.name("dateOfBirth")).sendKeys("07/07/2006");
        // fix to close date picked modal
        driver.findElement(By.xpath("//h1[text()='Edit account']")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
