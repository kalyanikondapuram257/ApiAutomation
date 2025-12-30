package serviceUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.ConfigReader;
import utils.EnvironmentUtil;

import java.io.FileOutputStream;
import java.io.PrintStream;

public final class CommonRequestSpec {
	
	// ConfigReader ConfigReader=new ConfigReader();
	public static String env="";

    private static RequestSpecification requestSpec;

    private CommonRequestSpec() {
        // prevent instantiation
    }

    /**
     * Returns a common reusable RequestSpecification
     */
    public static RequestSpecification getRequestSpec() {

        if (requestSpec == null) {
            try {
              
				// PrintStream logFile = new PrintStream(new FileOutputStream("target/api.log", true));
            	
                requestSpec = new RequestSpecBuilder()
                        .setBaseUri(CommonRequestSpec.getBaseUri())
                      //  .setContentType(ContentType.JSON)
                        .addHeader("Accept", "application/json")
                        .build();

            } catch (Exception e) {
                throw new RuntimeException("Failed to build RequestSpecification", e);
            }
        }
        return requestSpec;
    }

    public static String getBaseUri(){
    	//EnvironmentUtil.setEnvironment(env);
    	String baseurl= EnvironmentUtil.getBaseUri();
    	System.out.println("this is common request class method" +baseurl);
    	System.out.println("no environment" +env);
       // String env = ConfigReader.getProperty("env");
    	
        return baseurl;
    }
}