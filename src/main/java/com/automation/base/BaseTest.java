package com.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected static ExtentReports extentReports;
    protected ExtentTest test;

    @BeforeSuite
    public void beforeSuite() {
        logger.info("===== Test Suite Started =====");
        logger.info("Environment: " + ConfigReader.getEnvironment());
        logger.info("Base URI: " + ConfigReader.getBaseUri());

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        String reportPath = ConfigReader.getReportPath() + "/ExtentReport_" + timestamp + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("REST Assured API Test Report");
        reporter.config().setReportName("API Automation Tests");
        reporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Environment", ConfigReader.getEnvironment());
        extentReports.setSystemInfo("Base URI", ConfigReader.getBaseUri());
        extentReports.setSystemInfo("Execution Time", timestamp);

        logger.info("Extent Reports initialized at: " + reportPath);
    }

    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        logger.info("--- Test Started: " + method.getName() + " ---");
        test = extentReports.createTest(method.getName());
    }

    @AfterMethod
    public void afterMethod(java.lang.reflect.Method method, java.lang.reflect.ITestResult result) {
        if (result.isSuccess()) {
            logger.info("--- Test Passed: " + method.getName() + " ---");
            test.pass("Test passed successfully");
        } else if (result.getStatus() == 2) {
            logger.error("--- Test Failed: " + method.getName() + " ---");
            test.fail("Test failed with exception: " + result.getThrowable());
        } else {
            logger.warn("--- Test Skipped: " + method.getName() + " ---");
            test.skip("Test was skipped");
        }
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("===== Test Suite Completed =====");
        if (extentReports != null) {
            extentReports.flush();
            logger.info("Extent Report flushed successfully");
        }
    }
}
