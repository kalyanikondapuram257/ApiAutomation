package utils;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

import java.io.FileOutputStream;
import java.io.PrintStream;

public final class RestAssuredLogger {

    private RestAssuredLogger() {
        // Prevent object creation
    }

    /**
     * Enables centralized logging for Rest Assured
     */
    public static void initLogging() {

        PrintStream logFile;

        try {
            logFile = new PrintStream(
                    new FileOutputStream("target/api.log", true));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create API log file", e);
        }

        // Mask sensitive headers + log only on validation failure
        RestAssured.config = RestAssured.config()
                .logConfig(LogConfig.logConfig()
                        .blacklistHeader("Authorization")
                        .blacklistHeader("Cookie")
                        .enableLoggingOfRequestAndResponseIfValidationFails()
                );

        // Add global request & response logging filters
        RestAssured.filters(
                new RequestLoggingFilter(logFile),
                new ResponseLoggingFilter(logFile)
        );
    }
}