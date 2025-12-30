package spec;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import responseValiddationMethods.petServiceValidationMethods;
import serviceUtils.PetRequestSpecBuilder;
import utils.BaseTest;

public class petServiceSpecTest extends BaseTest{
	
	petServiceValidationMethods validationMethods=new petServiceValidationMethods();
	
@Test
public void uploadPetImage(){
	//given
	Response UploadPetImageresponse=PetRequestSpecBuilder.uploadPetImage();
	System.out.println(UploadPetImageresponse.getBody().asString());
	validationMethods.uploadImageValidation(UploadPetImageresponse);
	
	
	
}

@Test
public void addingPet() throws Exception{
	Response addingPetResponse=PetRequestSpecBuilder.addingPet();
	System.out.println(addingPetResponse.getBody().asString());
	validationMethods.addingNewPetValidation(addingPetResponse);

}

}
