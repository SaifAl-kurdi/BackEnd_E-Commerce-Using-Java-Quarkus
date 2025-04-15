package Wishlist.dto;

import Customer.dto.CustomerDTO;
import Product.dto.ProductDTO;
import Wishlist.model.WishlistModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class WishlistDTO {

    private int id;
    private ProductDTO product;
    private CustomerDTO customer;

    public WishlistDTO(WishlistModel wishlistModel) {
        this.id = wishlistModel.getId();

        if (wishlistModel.getCustomerID() != null) {
            this.customer = new CustomerDTO(wishlistModel.getCustomerID());
        }

        if (wishlistModel.getProductID() != null) {
            this.product = new ProductDTO(wishlistModel.getProductID());
        }
    }
}
