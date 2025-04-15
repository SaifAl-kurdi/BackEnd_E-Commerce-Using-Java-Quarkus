package Cart.controller;

import Cart.dto.CartDTO;
import Cart.service.CartService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("cart")
public class CartController {

    @Inject
    CartService cartService;

    @GET
    @Path("allTheCarts")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<CartDTO> allTheCarts() {
        return cartService.findAll();
    }

    @GET
    @Path("getCartById")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public CartDTO getCartById(@QueryParam("id") int id) {
        return cartService.findCartById(id);
    }

    @GET
    @Path("getCartByCustomerId")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Object[]> getCartByCustomerId(@QueryParam("customerId") int customerId) {
        return cartService.findCartByCustomerId(customerId);
    }

    @POST
    @Path("AddProductToCart")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public CartDTO AddProductToCart(
            @QueryParam("quantity") int quantity,
            @QueryParam("customerId") int customerId,
            @QueryParam("productId") int productId
    ) {
        return cartService.AddProductToCart(quantity, customerId, productId);
    }

    @PUT
    @Path("updateCart")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public CartDTO updateCart(
            @QueryParam("id") int id,
            @QueryParam("quantity") int quantity,
            @QueryParam("customerId") int customerId,
            @QueryParam("productId") int productId
    ) {
        return cartService.updateCart(id, quantity, customerId, productId);
    }

    @DELETE
    @Path("deleteCart")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public CartDTO deleteCart(@QueryParam("id") int id) {
        return cartService.deleteCart(id);
    }

    @DELETE
    @Path("deleteProductFromtCart")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public CartDTO deleteProductFromCart(@QueryParam("customerId") int customerId, @QueryParam("productId") int productId) {
        return cartService.deleteProductFromCart(customerId, productId);
    }
}
