package responsePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // getters, setters, toString, equals, hashCode
@NoArgsConstructor      // required for Jackson deserialization
@AllArgsConstructor
public class uploadPetImageResponsePojo{
//	{
//	    "code": 200,
//	    "type": "unknown",
//	    "message": "additionalMetadata: null\nFile uploaded to ./pexels-alexis-create-7351777.jpg, 473288 bytes"
//	}
	    private int code;
	    private String type;
	    private String message;
	}




