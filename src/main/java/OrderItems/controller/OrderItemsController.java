package OrderItems.controller;

import OrderItems.dto.OrderItemsDTO;
import OrderItems.service.OrderItemsService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("orderItems")
public class OrderItemsController {

    @Inject
    OrderItemsService orderItemsService;

    @GET
    @Path("allOrderItems")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<OrderItemsDTO> getAllOrderItems() {
        return orderItemsService.getOrderItemsModel();
    }

    // Here should get the data based on the order ID.

    // In this function I should return specific things.
    @GET
    @Path("theItemsByCustomerId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Object[]> getOrderItemsModelByCustomerId(@QueryParam("customerId") int CustomerId) {
        return orderItemsService.getOrderItemsModelByCustomerId(CustomerId);
    }

    @POST
    @Path("makeAnOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemsDTO makeAnOrder(
            @QueryParam("quantity") int quantity,
            @QueryParam("productId") int productId,
            @QueryParam("orderDetailsId") int orderDetailsId
    ) {
        return orderItemsService.makeOrderItemsDTO(quantity, productId, orderDetailsId);
    }

    // Here should Delete the data based on the order ID.

    // Here should Update the data based on the order ID.



}
