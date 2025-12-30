package serviceUtils;

//public final class ApiEndpoints {
//
//    private ApiEndpoints() {} // prevent instantiation
//
////    // Pet APIs
////    public static final String uploadPetImage = "/v2/pet/{petId}/uploadImage";
////    public static final String addingPetToStore = "/v2/pet";
////    public static final String updatingPetToStore = "/v2/pet";
////    public static final String findPetsByStatus = "/v2/pet/findByStatus";
////    public static final String findPetsById = "/v2/pet/{petId}";
////    public static final String updateAPetInStoreWithFormData = "/v2/pet/{petId}";
////    public static final String deletesAPet = "/v2/pet/{petId}";
////    
////
////    // Store APIs
////    public static final String GET_ORDERS = "/orders";
////    public static final String GET_ORDER_BY_ID = "/orders/{id}";
////    public static final String CREATE_ORDER = "/orders";
////
////    // User APIs
////    public static final String LOGIN = "/auth/login";
////    public static final String LOGOUT = "/auth/logout";
//}
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum ApiEndpoints {

    UPLOAD_PET_IMAGE("/v2/pet/{petId}/uploadImage"),
    PET_BASE("/v2/pet"),
    FIND_PET_BY_STATUS("/pet/findByStatus"),
    PET_BY_ID("/pet/{petId}");
	
	private final String path;
	
	ApiEndpoints(String path) {
        this.path = path;
    }
	
	public String getPath() {
        return path;
    }

	
	
}