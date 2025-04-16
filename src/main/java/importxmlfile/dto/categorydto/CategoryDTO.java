package importxmlfile.dto.categorydto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryDTO {

    @XmlElement(name = "Name")
    public String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
