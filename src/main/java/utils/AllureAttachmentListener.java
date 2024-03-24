package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import webDriver.Driver;

public class AllureAttachmentListener implements ITestListener {
    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenShot(byte[] screenShot){
        return screenShot;
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test " + result.getMethod().getMethodName() + " started.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        saveScreenShot(((TakesScreenshot) Driver.startDriver()).getScreenshotAs(OutputType.BYTES));
    }
}
