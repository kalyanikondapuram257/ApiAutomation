package utils;

import java.io.FileNotFoundException;
import io.qameta.allure.restassured.AllureRestAssured;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;

import io.restassured.RestAssured;
import serviceUtils.CommonRequestSpec;

public class BaseTest {
	
	@BeforeSuite
	public void beforeSuite() {
	    //AllureUtil.attachEnvironmentDetails();
		 ExtentReportManager.getInstance();
	}

	//public static void setup() throws FileNotFoundException {
	 // Set base URI dynamically using EnvironmentUtil
	@BeforeMethod(alwaysRun = true)
	public void setup(Method method) throws FileNotFoundException {
		// Pass default manual env (used only if Maven env is not provided)
		//RestAssured.filters(new AllureRestAssured());
		ExtentTest test = ExtentReportManager.getInstance()
                .createTest(method.getName());

        ExtentTestManager.setTest(test);
	    EnvironmentUtil.initializeEnvironment("PROD");

	    RestAssured.baseURI = EnvironmentUtil.getBaseUri();

	    System.out.println("ENV USED = " + System.getProperty("env"));
	    System.out.println("BASE URI = " + RestAssured.baseURI);
    // Logging requests and responses to a file
	   
 PrintStream logStream = new PrintStream(new FileOutputStream("target/api.log", true));
RestAssuredLogger.initLogging();

	
}
	 @AfterMethod(alwaysRun = true)
	    public void tearDownTest() {
	        ExtentTestManager.unload();
	    }

	    @AfterSuite(alwaysRun = true)
	    public void tearDownSuite() {
	        // Flush report once after suite execution
	        ExtentReportManager.getInstance().flush();
	    }
	
}
