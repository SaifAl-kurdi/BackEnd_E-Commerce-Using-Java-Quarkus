package OrderDetails.controller;

import OrderDetails.dto.OrderDetailsDTO;
import OrderDetails.service.OrderDetailsService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("orderDetails")
public class OrderDetailsController {

    @Inject
    OrderDetailsService orderDetailsService;

    @Transactional
    @Path("getAllOrders")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public List<Object[]> getAllOrderDetails() {
        return orderDetailsService.getOrderDetails();
    }

    @Transactional
    @Path("orderById")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public OrderDetailsDTO getOrderDetailsById(@QueryParam("id") int id) {
        return orderDetailsService.getOrderDetailsByOrderId(id);
    }

    @Transactional
    @Path("getOrderByCustomerId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public OrderDetailsDTO getOrderByCustomerId(@QueryParam("customerId") int customerId) {
        return orderDetailsService.getOrderDetailsByCustomerId(customerId);
    }

    @Path("createOrderDetails")
    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetailsDTO createOrderDetails(@QueryParam("customerId") int customerId) {
        return orderDetailsService.createOrderDetails(customerId);
    }

    @DELETE
    @Path("deleteOrderDetails")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDetailsDTO deleteOrderDetails(@QueryParam("id") int id) {
        return orderDetailsService.deleteOrderDetails(id);
    }

    // I want to make a study, if there is should delete based on the customer ID.

    // Here should I update on the order details.

    // I want to make a study, if there is should I update based on the customer ID.


}
