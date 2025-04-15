package Cart.dto;

import Cart.model.CartModel;
import Customer.dto.CustomerDTO;
import Customer.model.CustomerModel;
import Product.dto.ProductDTO;
import Product.model.ProductModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CartDTO {
    private int id;
    private int quantity;
    private ProductDTO productModel;
    private CustomerDTO customerModel;

    public CartDTO(CartModel cartModel) {
        this.id = cartModel.getId();
        this.quantity = cartModel.getQuantity();

        if (cartModel.getCustomerModel() != null) {
            this.customerModel = new CustomerDTO(cartModel.getCustomerModel());
        }
        if (cartModel.getProductModel() != null) {
            this.productModel = new ProductDTO(cartModel.getProductModel());
        }
    }
}

