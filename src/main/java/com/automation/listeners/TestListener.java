package com.automation.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestListener implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("===== Test Suite Started: " + context.getName() + " =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("===== Test Suite Finished: " + context.getName() + " =====");
        logger.info("Total Tests: " + context.getAllTestMethods().length);
        logger.info("Passed: " + context.getPassedTests().size());
        logger.info("Failed: " + context.getFailedTests().size());
        logger.info("Skipped: " + context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("✓ Test Passed: " + result.getMethod().getMethodName());
        logger.info("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("✗ Test Failed: " + result.getMethod().getMethodName());
        logger.error("Failure Reason: " + result.getThrowable());
        logger.error("Execution Time: " + (result.getEndMillis() - result.getStartMillis()) + " ms");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("⊘ Test Skipped: " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) {
            logger.warn("Skip Reason: " + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.warn("⚠ Test Failed but within success percentage: " + result.getMethod().getMethodName());
    }
}
