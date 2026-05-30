package com.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static Properties properties = new Properties();
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fis);
            logger.info("Configuration loaded successfully from: " + CONFIG_FILE_PATH);
            fis.close();
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + CONFIG_FILE_PATH, e);
            throw new RuntimeException("Configuration file not found: " + CONFIG_FILE_PATH);
        }
    }

    public static String getBaseUri() {
        return properties.getProperty("base.uri", "https://reqres.in/api");
    }

    public static String getEnvironment() {
        return properties.getProperty("environment", "qa");
    }

    public static String getUsername() {
        return properties.getProperty("username");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }

    public static String getOAuthToken() {
        return properties.getProperty("oauth.token");
    }

    public static long getRequestTimeout() {
        return Long.parseLong(properties.getProperty("request.timeout", "5000"));
    }

    public static int getMaxResponseTime() {
        return Integer.parseInt(properties.getProperty("max.response.time", "3000"));
    }

    public static boolean isParallelExecutionEnabled() {
        return Boolean.parseBoolean(properties.getProperty("parallel.execution", "true"));
    }

    public static int getThreadPoolSize() {
        return Integer.parseInt(properties.getProperty("thread.pool.size", "3"));
    }

    public static String getReportPath() {
        return properties.getProperty("report.path", "test-reports");
    }

    public static String getAllurePath() {
        return properties.getProperty("allure.path", "allure-results");
    }
}
