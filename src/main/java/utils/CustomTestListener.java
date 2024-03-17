package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import webDriver.Driver;

import java.io.File;
import java.io.IOException;

public class CustomTestListener implements ITestListener {
    private static void makeScreenshot(String method) {
        File screenshotFile = ((TakesScreenshot) Driver.startDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("./target/screenshots/" + method + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test " + result.getMethod().getMethodName() + " started.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test " + result.getMethod().getMethodName() + " failed.");
        makeScreenshot(result.getMethod().getMethodName());
    }
}
