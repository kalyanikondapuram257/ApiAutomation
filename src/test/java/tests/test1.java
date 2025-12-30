package tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import utils.ConfigReader;
import utils.RestAssuredLogger;

public class test1 {

//	@Test
//	public void verify() {
//		//String g =ConfigReader.getProperty("baseurl");
//		//System.out.println(g);
//		RestAssuredLogger.initLogging();
//RestAssured.baseURI=ConfigReader.getProperty("baseurl");
//RestAssured.given().header("accept", "application/json")
//.header("Content-Type", "application/json").baseUri("https://petstore.swagger.io").basePath("/v2/pet").
//body("{\r\n"
//		+ "  \"id\": 0,\r\n"
//		+ "  \"category\": {\r\n"
//		+ "    \"id\": 0,\r\n"
//		+ "    \"name\": \"string\"\r\n"
//		+ "  },\r\n"
//		+ "  \"name\": \"doggie\",\r\n"
//		+ "  \"photoUrls\": [\r\n"
//		+ "    \"string\"\r\n"
//		+ "  ],\r\n"
//		+ "  \"tags\": [\r\n"
//		+ "    {\r\n"
//		+ "      \"id\": 0,\r\n"
//		+ "      \"name\": \"string\"\r\n"
//		+ "    }\r\n"
//		+ "  ],\r\n"
//		+ "  \"status\": \"available\"\r\n"
//		+ "}").when().post().then().statusCode(200);
//


	}

//}
