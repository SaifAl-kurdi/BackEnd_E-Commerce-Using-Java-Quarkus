package Cart.repository;

import Cart.model.CartModel;
import Customer.model.CustomerModel;
import OrderDetails.repository.OrderDetailsRepository;
import Product.model.ProductModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CartRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    OrderDetailsRepository orderDetailsRepository;

    public List<CartModel> allTheCarts() {
        return entityManager.createQuery("SELECT c FROM CartModel c", CartModel.class).getResultList();
    }

    public CartModel findCartById(int cartId) {
        return entityManager.find(CartModel.class, cartId);
    }

    public List<Object[]> findCartByCustomerId(int customerId) {
        List<Object[]> objects = entityManager.createQuery("Select w.productModel.id, w.productModel.name, w.productModel.price, w.quantity FROM CartModel w where w.customerModel.id = :customerId", Object[].class)
                .setParameter("customerId", customerId)
                .getResultList();

        for (Object[] object: objects) {
            int id = Integer.parseInt(object[0].toString());
            String productName = object[1].toString();
            float price = Float.parseFloat(object[2].toString());
            int quantity = Integer.parseInt(object[3].toString());

            double totalPrice = orderDetailsRepository.calculateTotalPrice(quantity, price);
            System.out.println("The product Id " + id + " \nThe product ID: " + productName + " \nprice for the product: " + price + " \nThe quantity: " + quantity + " \nTotal Price:  " + totalPrice);
            System.out.println("_______________________________________________________________");
        }
        return objects;
    }

    public CartModel AddProductToCart(int quantity, int customerId, int productId) {
        List<CartModel> existing = entityManager.createQuery("SELECT w FROM CartModel w where w.productModel.id = :productId AND w.customerModel.id = :customerId", CartModel.class)
                .setParameter("productId", productId).setParameter("customerId", customerId).getResultList();

        if (!existing.isEmpty()) {
            CartModel cartItem = existing.get(0);
            int newQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(newQuantity);
            return cartItem;
        }

        CartModel cartModel = new CartModel();
        cartModel.setQuantity(quantity);
        if (customerId != 0) {
            CustomerModel customerModel = getCustomerRefById(customerId);
            cartModel.setCustomerModel(customerModel);
        }
        if (productId != 0) {
            ProductModel productModel = getProductRefById(productId);
            cartModel.setProductModel(productModel);
        }
        entityManager.persist(cartModel);
        return cartModel;
    }

    public CartModel removeCart(int cartId) {
        CartModel cartModel = entityManager.find(CartModel.class, cartId);
        entityManager.remove(cartModel);
        return cartModel;
    }

    public CartModel removeProductFromCart(int customerId, int productId) {
        CartModel cartModel = entityManager.createQuery("SELECT w FROM CartModel w Where w.customerModel.id = :customerId AND w.productModel.id = :productId", CartModel.class)
                .setParameter("customerId", customerId)
                .setParameter("productId", productId)
                .getSingleResult();

        entityManager.remove(cartModel);
        return cartModel;
    }

    public CartModel updateCart(CartModel cartModel) {
        entityManager.merge(cartModel);
        return cartModel;
    }

    public CustomerModel getCustomerRefById(int customerId) {
        return entityManager.getReference(CustomerModel.class, customerId);
    }
    public ProductModel getProductRefById(int productId) {
        return entityManager.getReference(ProductModel.class, productId);
    }
}

