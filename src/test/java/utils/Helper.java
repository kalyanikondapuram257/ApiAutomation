package utils;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper{
	public static ObjectMapper mapper = new ObjectMapper();
	public static <T> T getObjectFromJson(String fileName, Class<T> clazz) {
        try {
        	 return mapper.readValue(new File(fileName), clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON payload", e);
        }
    }
	
	
	
	public static String convertResponseToString(Response response) {
		
		String jsonResponse = response.getBody().asString();
		
		return jsonResponse;
	}
	
	public static Map<String,String> addheader() {
		Map<String,String> map=new HashMap<String,String>();
		map.put("Content-Type", "application/json");
		//map.put("accept", "application/json");
		return map;

	}
	
	public static String getRequestPayload(String filePath, String sheetName, String ColumnName) {
	// final Object key = null;
	String requestBody = null;
		List<Map<String, String>> testData =
		        ExcelUtils.getTestData(
		        		filePath ,
		                sheetName);

		for (Map<String, String> data : testData) {
		    
			 requestBody = data.get(ColumnName);
		   }
		return requestBody;
		 }
	
}
