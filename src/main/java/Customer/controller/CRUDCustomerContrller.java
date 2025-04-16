package Customer.controller;

import Customer.dto.CustomerDTO;
import Customer.service.CRUDCustomerService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("CRUD")
public class CRUDCustomerContrller {

    @Inject
    CRUDCustomerService crudCustomerService;

    @GET
    @Transactional
    @Path("AllTheCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> findAllTheCustomer() {
        return crudCustomerService.findAll();
    }

    @GET
    @Transactional
    @Path("findById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO  findById(@QueryParam("id") int id) {
        return crudCustomerService.findById(id);
    }

    @GET
    @Transactional
    @Path("findByEmail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO findByEmail(@QueryParam("email") String email) {
        return crudCustomerService.findByEmail(email);
    }

    @POST
    @Transactional
    @Path("addCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO addCustomer(
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("email") String email,
            @QueryParam("address") String address,
            @QueryParam("phoneNumber") String phoneNumber
    ) {
        return crudCustomerService.addCustomer(firstName, lastName, email, address, phoneNumber);
    }

    @PUT
    @Transactional
    @Path("updateCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO updateCustomer(
            @QueryParam("id") int id,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("email") String email,
            @QueryParam("address") String address,
            @QueryParam("phoneNumber") String phoneNumber
    ) {
        if (id == 0) {
            throw new WebApplicationException("There is no ID");
        }
        return crudCustomerService.updateCustomer(id, firstName, lastName, email, address, phoneNumber);
    }

    @DELETE
    @Transactional
    @Path("deleteCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerDTO deleteCustomer(@QueryParam("id") int id) {
        return crudCustomerService.deleteCustomer(id);
    }
}