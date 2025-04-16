package importxmlfile.dto.customerdto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDTO {

    @XmlElement(name = "firstName")
    private String firstName;

    @XmlElement(name = "lastName")
    private String lastName;

    @XmlElement(name = "email")
    private String email;

    @XmlElement(name = "address")
    private String address;

    @XmlElement(name = "phoneNumber")
    private int phoneNumber;

    public String getFirstNameDTO() {
        return firstName;
    }
    public void setFirstNameDTO(String firstName) {
        this.firstName = firstName;
    }

    public String getLastNameDTO() {
        return lastName;
    }
    public void setLastNameDTO(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailDTO() {
        return email;
    }
    public void setEmailDTO(String email) {
        this.email = email;
    }

    public String getAddressDTO() {
        return address;
    }
    public void setAddressDTO(String address) {
        this.address = address;
    }

    public int getPhoneNumberDTO() {
        return phoneNumber;
    }
    public void setPhoneNumberDTO(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
