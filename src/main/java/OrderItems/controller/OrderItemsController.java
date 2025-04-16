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

    @GET
    @Path("orderItemsById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemsDTO getAllOrderItemsByCustomerId(@QueryParam("id") int customerId) {
        return orderItemsService.getOrderItemsById(customerId);
    }

    @GET
    @Path("theItemsByCustomerId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Object[]> getOrderItemsModelByCustomerId(@QueryParam("customerId") int customerId) {
        return orderItemsService.getOrderItemsModelByCustomerId(customerId);
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

    @DELETE
    @Path("deleteOrderItemsById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemsDTO deleteOrderItemsById(@QueryParam("id") int id) {
        return orderItemsService.deleteOrderItemsById(id);
    }

    @DELETE
    @Path("deleteOrderItemsByOrderDetailsId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<OrderItemsDTO> deleteOrderItemsByOrderDetailsId(@QueryParam("orderDetailsId") int id) {
        return orderItemsService.deleteOrderItemsByOrderDetailsId(id);
    }

    @PUT
    @Path("updateOrderItemsById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public OrderItemsDTO updateOrderItemsById(
            @QueryParam("id") int id,
            @QueryParam("quantity") int quantity,
            @QueryParam("productId") int productId,
            @QueryParam("orderDetailsId") int orderDetailsId
    ) {
       return orderItemsService.updateOrderItemsById(id, quantity, productId, orderDetailsId);
    }
}