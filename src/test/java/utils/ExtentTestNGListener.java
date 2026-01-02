package utils;

import com.aventstack.extentreports.*;
import org.testng.*;
import utils.ExtentReportManager;
import utils.ExtentTestManager;

public class ExtentTestNGListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentReportManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest()
                .fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest()
                .skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
