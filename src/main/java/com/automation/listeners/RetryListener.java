package com.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryListener implements IRetryAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(RetryListener.class);
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            logger.warn("Retrying test: " + result.getMethod().getMethodName() +
                       " | Attempt: " + retryCount + " of " + MAX_RETRY_COUNT);
            return true;
        }
        return false;
    }
}
