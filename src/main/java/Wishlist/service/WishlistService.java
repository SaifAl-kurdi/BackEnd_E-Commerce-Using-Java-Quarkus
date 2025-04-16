package Wishlist.service;

import Wishlist.dto.WishlistDTO;
import Wishlist.model.WishlistModel;
import Wishlist.repository.WishlistRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class WishlistService {

    @Inject
    WishlistRepository wishlistRepository;

    public List<WishlistDTO> allTheWishlists() {
        List<WishlistModel> wishlistModel = wishlistRepository.getAllWishlist();
        return wishlistModel.stream().map(WishlistDTO::new).collect(Collectors.toList());
    }

    public WishlistDTO getWishlistById(int id) {
        WishlistModel wishlistModel = wishlistRepository.getWishlistModelById(id);
        return new WishlistDTO(wishlistModel);
    }

    public List<Object[]> getAllWishlistFromTheCustomerId(int customerId) {
        return wishlistRepository.getAllWishlistFromTheCustomerId(customerId);
    }

    public WishlistDTO addToWishlist(int customerId, int productId) {
        WishlistModel wishlistModel = wishlistRepository.addToWishlist(customerId, productId);;
        return new WishlistDTO(wishlistModel);
    }

    public WishlistDTO deleteWishlist (int id) {
        WishlistModel wishlistModel = wishlistRepository.deleteWishlistById(id);
        return new WishlistDTO(wishlistModel);
    }

    public WishlistDTO deleteWishlistByCustomerIdAndProductId(int productId, int customerId) {
        WishlistModel wishlistModel = wishlistRepository.deleteWishlistByCustomerIdAndProductId(productId, customerId);
        return new WishlistDTO(wishlistModel);
    }
}