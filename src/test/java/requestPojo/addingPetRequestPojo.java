package requestPojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale.Category;

import javax.swing.text.html.HTML.Tag;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class addingPetRequestPojo {
	private Long id;
    private CategoryRequestPojo category;
    private String name;
    private List<String> photoUrls;
    private List<TagRequestPojo> tags;
    private String status;
}
