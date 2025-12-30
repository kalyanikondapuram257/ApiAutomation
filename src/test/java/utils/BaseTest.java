package utils;

import java.io.FileNotFoundException;
import io.qameta.allure.restassured.AllureRestAssured;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;
import serviceUtils.CommonRequestSpec;

public class BaseTest {
	
	@BeforeSuite
	public void beforeSuite() {
	    AllureUtil.attachEnvironmentDetails();
	}

	//public static void setup() throws FileNotFoundException {
	 // Set base URI dynamically using EnvironmentUtil
	@BeforeTest
	public void setup() throws FileNotFoundException {
		// Pass default manual env (used only if Maven env is not provided)
		RestAssured.filters(new AllureRestAssured());
	    EnvironmentUtil.initializeEnvironment("PROD");

	    RestAssured.baseURI = EnvironmentUtil.getBaseUri();

	    System.out.println("ENV USED = " + System.getProperty("env"));
	    System.out.println("BASE URI = " + RestAssured.baseURI);
    // Logging requests and responses to a file
	   
 PrintStream logStream = new PrintStream(new FileOutputStream("target/api.log", true));
RestAssuredLogger.initLogging();

	
}
	
}
