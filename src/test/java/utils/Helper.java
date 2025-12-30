package utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

import java.io.File;

public class Helper{
	public static <T> T getObjectFromJson(String fileName, Class<T> clazz) {
        try {
        	 return new ObjectMapper().readValue(new File(fileName), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON payload", e);
        }
    }
	
	
	
	public static String convertResponseToString(Response response) {
		
		String jsonResponse = response.getBody().asString();
		
		return jsonResponse;
	}
	
}
