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

    @DELETE
    @Path("deleteOrderDetailsByCustomerId")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public OrderDetailsDTO deleteOrderDetailsByCustomerId(@QueryParam("customerId") int customerId) {
        return orderDetailsService.deleteOrderDetailsByCustomerId(customerId);
    }

    @PUT
    @Path("updateOrderDet")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetailsDTO updateOrderDetails(
            @QueryParam("orderId") int orderId,
            @QueryParam("total") float total,
            @QueryParam("customerId") int customerId
            ) {
        return orderDetailsService.updateOrderDetails(orderId, total, customerId);
    }

    @PUT
    @Path("updateOrderDetByCustomerId")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetailsDTO updateOrderDetailsByCustomerId(@QueryParam("customerId") int customerId, @QueryParam("total") float total) {
        return orderDetailsService.updateOrderDetailsByCustomerId(customerId, total);
    }
}