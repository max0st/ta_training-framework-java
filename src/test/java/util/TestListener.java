package util;

import driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Starting test: " + iTestResult.getMethod().getMethodName());
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("Test Passed: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("Test Failed: " + iTestResult.getMethod().getMethodName());
        saveScreenshot(iTestResult);
    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("Test Skipped: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test Failed but within success percentage: " + iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("Start of Test TAG: " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("End of Test TAG: " + iTestContext.getName());
    }

    private void saveScreenshot(ITestResult iTestResult) {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            String fileName = iTestResult.getMethod().getMethodName() + "_" + getCurrentTimeAsString() +
                    (iTestResult.getStatus() == ITestResult.FAILURE ? "_FAILED" : "_SUCCESS") + ".png";
            FileUtils.copyFile(screenCapture, new File(".//target/screenshots/" + fileName));
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }


    private String getCurrentTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }
}
