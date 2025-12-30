package responseValiddationMethods;

import java.util.List;

import org.testng.asserts.SoftAssert;


import io.restassured.response.Response;
import responsePojo.TagResponsePojo;
import responsePojo.addingPetResponsePojo;
import responsePojo.uploadPetImageResponsePojo;
import tools.jackson.databind.ObjectMapper;
import utils.Helper;

//import tools.jackson.databind.ObjectMapper;

public class petServiceValidationMethods {
	
	public SoftAssert softAssert = new SoftAssert();
	
	
	 uploadPetImageResponsePojo uploadResponse;
	 addingPetResponsePojo newPetResponse;
	 public static  Long newPetId;
	 
	public void uploadImageValidation(Response response) {
		

		try {
		    // Convert response body to JSON string
		    String jsonResponse = response.getBody().asString();
//System.out.println(jsonResponse);
		    // Create ObjectMapper instance
		    ObjectMapper mapper = new ObjectMapper();

		    // Deserialize JSON into POJO
		    uploadResponse = mapper.readValue(jsonResponse, uploadPetImageResponsePojo.class);

		    // Optional: print fields for verification
		    

		} catch (Exception e) {
		    e.printStackTrace();
		    throw new RuntimeException("Failed to deserialize response into POJO", e);
		}
		  
		 
		    softAssert.assertEquals(uploadResponse.getCode(), 200);
		    softAssert.assertEquals(uploadResponse.getType(), "unknown");
		    softAssert.assertTrue(uploadResponse.getMessage().contains("uploaded"));

		    softAssert.assertAll(); 
		
	}
	
public void addingNewPetValidation(Response response) {
		

		try {
		    // Convert response body to JSON string
		    
//System.out.println(jsonResponse);
		    // Create ObjectMapper instance
		    ObjectMapper mapper = new ObjectMapper();
		   
		    // Deserialize JSON into POJO
		    newPetResponse = mapper.readValue(Helper.convertResponseToString(response), addingPetResponsePojo.class);

		    // Optional: print fields for verification
		    

		} catch (Exception e) {
		    e.printStackTrace();
		    throw new RuntimeException("Failed to deserialize response into POJO", e);
		}
		  
		 List<TagResponsePojo> tags=newPetResponse.getTags();
		  newPetId=newPetResponse.getId();
		    softAssert.assertEquals(newPetResponse.getCategory().getName(),"string");
		  
		   // softAssert.assertEquals(tags.get(0).getId(),0);
		    
		    softAssert.assertEquals(tags.get(0).getId(), Long.valueOf(0));

		    
		    softAssert.assertEquals(newPetResponse.getStatus(),"available");

		    softAssert.assertAll(); 
		
	}


}
