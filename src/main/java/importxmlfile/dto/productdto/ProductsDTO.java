package importxmlfile.dto.productdto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsDTO {

    @XmlElement(name = "Product")
    private List<ProductDTO> productDTO;

    public List<ProductDTO> getProductDTO() {
        return productDTO;
    }
    public void setProductDTO(List<ProductDTO> productDTO) {
        this.productDTO = productDTO;
    }
}
