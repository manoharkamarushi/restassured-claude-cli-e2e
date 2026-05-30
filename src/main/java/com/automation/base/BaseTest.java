package com.automation.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.ITestResult;

public class BaseTest {
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite
    public void beforeSuite() {
        logger.info("===== Test Suite Started =====");
        logger.info("Environment: " + ConfigReader.getEnvironment());
        logger.info("Base URI: " + ConfigReader.getBaseUri());
    }

    @BeforeMethod
    public void beforeMethod(java.lang.reflect.Method method) {
        logger.info("--- Test Started: " + method.getName() + " ---");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        if (result.isSuccess()) {
            logger.info("--- Test Passed: " + testName + " ---");
        } else if (result.getStatus() == 2) {
            logger.error("--- Test Failed: " + testName + " ---");
            logger.error("Exception: " + result.getThrowable());
        } else {
            logger.warn("--- Test Skipped: " + testName + " ---");
        }
    }

    @AfterSuite
    public void afterSuite() {
        logger.info("===== Test Suite Completed =====");
    }
}
