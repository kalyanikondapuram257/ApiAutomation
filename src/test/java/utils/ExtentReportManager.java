package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;

public final class ExtentReportManager {

    private static ExtentReports extent;

    private ExtentReportManager() {}

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = Paths.get(
                    System.getProperty("user.dir"),
                    "test-output",
                    "ExtentReport.html"
            ).toString();

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("REST API Automation Report");
            spark.config().setDocumentTitle("PetStore API Tests");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "PETSTORE");
            extent.setSystemInfo("Tester", "Jaya Krishna");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}

