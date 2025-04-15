package Customer.repository;

import Customer.model.CustomerModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CRUDCustomerRepository {

    @Inject
    EntityManager entityManager;

    public List<CustomerModel> findAll() {
        List<CustomerModel> customers = entityManager.createQuery("SELECT c FROM CustomerModel c", CustomerModel.class).getResultList();
        return customers;
    }

    public CustomerModel findById(int id) {
        return entityManager.find(CustomerModel.class, id);
    }

    public CustomerModel findByEmail(String email) {
        List<CustomerModel> customers = entityManager.createQuery("SELECT c FROM CustomerModel c where c.email = :email", CustomerModel.class).setParameter("email", email).getResultList();
        return customers.get(0);
    }

    public CustomerModel addCustomer(String firstName, String lastName, String email, String address, String phoneNumber) {
        CustomerModel customer = new CustomerModel();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        entityManager.persist(customer);
        return customer;
    }

    public CustomerModel updateCustomer(CustomerModel customer) {
        entityManager.merge(customer);
        return customer;
    }

    public CustomerModel deleteCustomer(int id) {
        CustomerModel customer = findById(id);
        entityManager.remove(customer);
        return customer;
    }
}