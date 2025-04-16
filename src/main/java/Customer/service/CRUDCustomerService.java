package Customer.service;

import Customer.dto.CustomerDTO;
import Customer.model.CustomerModel;
import Customer.repository.CRUDCustomerRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CRUDCustomerService {

    @Inject
    CRUDCustomerRepository crudCustomerRepository;

    public List<CustomerDTO> findAll() {
        List<CustomerModel> list = crudCustomerRepository.findAll();
        return list.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    public CustomerDTO findById(int id) {
        CustomerModel customer = crudCustomerRepository.findById(id);
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(customer);
    }

    public CustomerDTO findByEmail(String email) {
        CustomerModel customer = crudCustomerRepository.findByEmail(email);
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(customer);
    }

    public CustomerDTO addCustomer(String firstName, String lastName, String email, String address, String phoneNumber) {
        CustomerModel customer = crudCustomerRepository.addCustomer(firstName, lastName, email, address, phoneNumber);
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(customer);
    }

    public CustomerDTO updateCustomer(int id, String firstName, String lastName, String email, String address, String phoneNumber) {
        CustomerModel customer = crudCustomerRepository.findById(id);
        if (customer == null) {
            return null;
        }

        if (firstName != null) customer.setFirstName(firstName);
        if (lastName != null) customer.setLastName(lastName);
        if (email != null) customer.setEmail(email);
        if (address != null)  customer.setAddress(address);
        if (phoneNumber != null) customer.setPhoneNumber(phoneNumber);

        CustomerModel updatedCustomerModel = crudCustomerRepository.updateCustomer(customer);
        return new CustomerDTO(updatedCustomerModel);
    }

    public CustomerDTO deleteCustomer(int id) {
        CustomerModel customer = crudCustomerRepository.deleteCustomer(id);
        if (customer == null) {
            return null;
        }
        return new CustomerDTO(customer);
    }
}