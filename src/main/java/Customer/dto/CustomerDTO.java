package Customer.dto;

import Customer.model.CustomerModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CustomerDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;

    public CustomerDTO(CustomerModel customerModel) {
        this.id = customerModel.getId();
        this.firstName = customerModel.getFirstName();
        this.lastName = customerModel.getLastName();
        this.email = customerModel.getEmail();
        this.address = customerModel.getAddress();
        this.phoneNumber = customerModel.getPhoneNumber();
    }
}