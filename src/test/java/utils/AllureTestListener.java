package utils;

import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        // Attach the failure reason to Allure report
        if (result.getThrowable() != null) {
            Allure.addAttachment("Failure Reason", result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (result.getThrowable() != null) {
            Allure.addAttachment("Skipped Reason", result.getThrowable().getMessage());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        // Optional: attach environment or other info
        Allure.addAttachment("Environment", System.getProperty("env", "DEV"));
    }
}
