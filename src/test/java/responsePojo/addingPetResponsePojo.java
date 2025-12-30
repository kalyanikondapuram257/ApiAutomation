package responsePojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class addingPetResponsePojo {
	
	 private Long id;
	    private CategoryResponsePojo category;
	    private String name;
	    private List<String> photoUrls;
	    private List<TagResponsePojo> tags;
	    private String status;
}
