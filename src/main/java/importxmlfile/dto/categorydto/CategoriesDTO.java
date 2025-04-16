package importxmlfile.dto.categorydto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesDTO {

    @XmlElement(name = "Category")
    private List<CategoryDTO> categoryDTOList;

    public List<CategoryDTO> getCategoryDTOList() {
        return categoryDTOList;
    }

    public void setCategoryDTOList(List<CategoryDTO> categoryDTOList) {
        this.categoryDTOList = categoryDTOList;
    }
}
