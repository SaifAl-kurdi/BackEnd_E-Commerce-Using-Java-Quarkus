package importxmlfile.dto.customerdto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersDTO {

    @XmlElement(name = "Customer")
    private List<CustomerDTO> customer;

    public List<CustomerDTO> getCustomer() {
        return customer;
    }
    public void setCustomer(List<CustomerDTO> customer) {
        this.customer = customer;
    }
}
