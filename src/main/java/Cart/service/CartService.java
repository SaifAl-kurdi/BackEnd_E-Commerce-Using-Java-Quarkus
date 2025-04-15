package Cart.service;

import Cart.dto.CartDTO;
import Cart.model.CartModel;
import Cart.repository.CartRepository;
import Customer.model.CustomerModel;
import Product.model.ProductModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CartService {

    @Inject
    CartRepository cartRepository;

    public List<CartDTO> findAll() {
        List<CartModel> cartModel = cartRepository.allTheCarts();
        return cartModel.stream().map(CartDTO::new).collect(Collectors.toList());
    }

    public CartDTO findCartById(int id) {
        CartModel cartModel = cartRepository.findCartById(id);
        return new CartDTO(cartModel);
    }

    public List<Object[]> findCartByCustomerId(int customerId) {
        return cartRepository.findCartByCustomerId(customerId);
    }

    public CartDTO AddProductToCart(int quantity, int customerId, int productId) {
        CartModel cartModel = cartRepository.AddProductToCart(quantity, customerId, productId);
        return new CartDTO(cartModel);
    }

    public CartDTO deleteCart(int id) {
        CartModel cartModel = cartRepository.removeCart(id);
        return new CartDTO(cartModel);
    }

    public CartDTO deleteProductFromCart(int customerId, int productId) {
        CartModel cartModel = cartRepository.removeProductFromCart(customerId, productId);
        return new CartDTO(cartModel);
    }

    public CartDTO updateCart(int id, int quantity, int customerId, int productId) {
        CartModel cartModel = cartRepository.findCartById(id);

        if (quantity != 0) cartModel.setQuantity(quantity);

        if (customerId != 0) {
            CustomerModel customerModel = cartRepository.getCustomerRefById(customerId);
            cartModel.setCustomerModel(customerModel);
        }
        if (productId != 0) {
            ProductModel productModel = cartRepository.getProductRefById(productId);
            cartModel.setProductModel(productModel);
        }
        CartModel updatedCartModel = cartRepository.updateCart(cartModel);

        return new CartDTO(updatedCartModel);
    }
}
