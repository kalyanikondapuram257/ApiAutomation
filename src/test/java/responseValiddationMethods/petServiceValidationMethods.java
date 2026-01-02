package responseValiddationMethods;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.testng.asserts.SoftAssert;


import io.restassured.response.Response;
import responsePojo.TagResponsePojo;
import responsePojo.addingPetResponsePojo;
import responsePojo.uploadPetImageResponsePojo;
//import tools.jackson.databind.ObjectMapper;
import utils.Helper;
import com.fasterxml.jackson.core.type.TypeReference;


public class petServiceValidationMethods {
	
	public SoftAssert softAssert = new SoftAssert();
	
	
	 uploadPetImageResponsePojo uploadResponse;
	 addingPetResponsePojo newPetResponse;
	 public static  Long newPetId;
	 ObjectMapper mapper = new ObjectMapper();
	 addingPetResponsePojo findPetByStatusResponse;
	public void uploadImageValidation(Response response) {
		

		try {
		    // Convert response body to JSON string
		    String jsonResponse = response.getBody().asString();
//System.out.println(jsonResponse);
		    // Create ObjectMapper instance
		    //ObjectMapper mapper = new ObjectMapper();

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
		   // ObjectMapper mapper = new ObjectMapper();
		   
		    // Deserialize JSON into POJO
		    newPetResponse =mapper.readValue(Helper.convertResponseToString(response), addingPetResponsePojo.class);

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

 public void findPetByStatusValidation(Response response) {
	final List<addingPetResponsePojo> findResponse;

	try {
	    // Convert response body to JSON string
	    String jsonResponse = response.getBody().asString();
//System.out.println(jsonResponse);
	    // Create ObjectMapper instance
	  

	    // Deserialize JSON into POJO
  findResponse = mapper.readValue(Helper.convertResponseToString(response), new TypeReference<List<addingPetResponsePojo>>() {});
		 //.readValue(Helper.convertResponseToString(response), new TypeReference<List<addingPetResponsePojo>>() {});

	    // Optional: print fields for verification
	    

	} catch (Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException("Failed to deserialize response into POJO", e);
	}
	  
	List<Long> petIdList = new ArrayList<>();
 for(addingPetResponsePojo pet : findResponse) {
	if ("available".equalsIgnoreCase(pet.getStatus())) {
	    petIdList.add(pet.getId());
	    String nameOfPet=pet.getName();
	   // softAssert.assertEquals(nameOfPet, "doggie");
	    softAssert.assertAll(); 
	}
	
	if (pet.getId()==708682) {
		String nameCategory=pet.getCategory().getName();
		softAssert.assertEquals(nameCategory, "kangs name");
		 softAssert.assertAll(); 
	}
	//int numberOfPetsAvailable=petIdList.size();
	//System.out.println(numberOfPetsAvailable);
	}
int numberOfPetsAvailable=petIdList.size();
System.out.println(numberOfPetsAvailable);
}
 //for huge Array of json responses while deserializing in the mapper.readvalue method we should pass the 
 //List of pojo object lass instead of single pojo class obect 
 //we should make use of foreach loop and from List of poo object single Json response should be passed to the reference variable.
 //based on the condition we can validate any attribute 
}
