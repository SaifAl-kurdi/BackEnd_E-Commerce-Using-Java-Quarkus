package Wishlist.controller;

import Wishlist.dto.WishlistDTO;
import Wishlist.service.WishlistService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("wishlist")
public class WishlistController {

    @Inject
    WishlistService wishlistService;

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public List<WishlistDTO> getAllWishlist() {
        return wishlistService.allTheWishlists();
    }

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("id")
    public WishlistDTO getWishlistById(@QueryParam("id") int id) {
        return wishlistService.getWishlistById(id);
    }

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Path("customerId")
    public List<Object[]> getWishlistByCustomerId(@QueryParam("customerId") int customerID) {
        return wishlistService.getAllWishlistFromTheCustomerId(customerID);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("addToWishlist")
    public WishlistDTO addToWishlist(
            @QueryParam("customerId") int customerId,
            @QueryParam("productId") int productId
    ) {
        return wishlistService.addToWishlist(customerId, productId);
    }

    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("deleteFromWishlist")
    public WishlistDTO deleteFromWishlist(@QueryParam("deleteById") int deleteById ) {
        return wishlistService.deleteWishlist(deleteById);
    }

    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("deleteWishlistByClientAndProductIds")
    public WishlistDTO deleteFromWishlistByClientIdAndProductId(
            @QueryParam("customerId") int deleteByCustomerId,
            @QueryParam("productId") int deleteByProductId
            ) {
        return wishlistService.deleteWishlistByCustomerIdAndProductId(deleteByCustomerId, deleteByProductId);
    }
}