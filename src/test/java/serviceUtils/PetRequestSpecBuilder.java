package serviceUtils;

import java.io.File;
import java.util.Random;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import requestPojo.addingPetRequestPojo;
import utils.Helper;

public class PetRequestSpecBuilder{
	
	public static Response uploadPetImage() {
		
		Random random = new Random();
        int petid = random.nextInt(1000); // generates 0 to 999
        
		File imageFile = new File(".//src//test//resources//Image//pexels-michael-pointner-134459625-29676412.jpg");
	Response uploadImageResponse=RestAssured.given()
    .spec(CommonRequestSpec.getRequestSpec())
    .header("Content-Type", "multipart/form-data")
    .basePath(ApiEndpoints.UPLOAD_PET_IMAGE.getPath())
    .pathParam("petId", petid)
    .multiPart("file", imageFile)
    .when()
    .post();  
	return uploadImageResponse;
	}
	
	public static Response addingPet() throws Exception{
		 // Load JSON dynamically into Pet object
		addingPetRequestPojo newPet = Helper.getObjectFromJson("src/test/resources/Payloads/addingPet.json", addingPetRequestPojo.class);

        // Send API request using RestAssured
        Response newlyAddedPetResponse = RestAssured.given()
                .spec(CommonRequestSpec.getRequestSpec())
                .headers(Helper.addheader())
                .body(newPet)
                .basePath(ApiEndpoints.PET_BASE.getPath())
                .when()
                .post();
            
return newlyAddedPetResponse;
       
    }
	
	public static Response addingPetExcel() throws Exception{
		 // Load JSON dynamically into Pet object
		//addingPetRequestPojo newPet = Helper.getObjectFromJson("src/test/resources/Payloads/addingPet.json", addingPetRequestPojo.class);

       // Send API request using RestAssured
       Response newlyAddedPetExcelResponse = RestAssured.given()
               .spec(CommonRequestSpec.getRequestSpec())
               .headers(Helper.addheader())
               .body(Helper.getRequestPayload("addingPetToStore.xlsx", "AddingPet", "RequestBody"))
               .basePath(ApiEndpoints.PET_BASE.getPath())
               .when()
               .post();
           
    return newlyAddedPetExcelResponse;
      
   }
	
	public static Response findPetByStatus() throws Exception{
		 
		 // Send API request using RestAssured
       Response findPetByStatusResponse = RestAssured.given()
               .spec(CommonRequestSpec.getRequestSpec())
               .queryParam("status", "available")
               .basePath(ApiEndpoints.FIND_PET_BY_STATUS.getPath())
               .when()
               .get();
           
return findPetByStatusResponse;
	}
}


