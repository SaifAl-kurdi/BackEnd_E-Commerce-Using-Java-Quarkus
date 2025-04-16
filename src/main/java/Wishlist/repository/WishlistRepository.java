package Wishlist.repository;

import Customer.model.CustomerModel;
import Product.model.ProductModel;
import Wishlist.model.WishlistModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class WishlistRepository {

    @Inject
    EntityManager entityManager;

    public List<WishlistModel> getAllWishlist() {
        return entityManager.createQuery("SELECT c FROM WishlistModel c", WishlistModel.class).getResultList();
    }

    public WishlistModel getWishlistModelById(int wishlistId) {
        return entityManager.find(WishlistModel.class, wishlistId);
    }

    // Nested of return the list of objects (I can make the DTO for the Item that return from the client side and put the name and description)
    public List<Object[]> getAllWishlistFromTheCustomerId(int customerId) {
        return entityManager.createQuery("SELECT w.productID.name, w.productID.description FROM WishlistModel w WHERE w.customerID.id = :customerId", Object[].class)
                .setParameter("customerId", customerId).getResultList();
    }

    public WishlistModel addToWishlist(int clientId, int productId) {
        if (clientId <= 0 || productId <= 0) {
            throw new IllegalArgumentException("Customer ID and Product ID must be positive");
        }

        WishlistModel wishlistModel = new WishlistModel();

        CustomerModel customerModel = getCustomerRefById(clientId);
        ProductModel productModel = getProductRefById(productId);

        wishlistModel.setCustomerID(customerModel);
        wishlistModel.setProductID(productModel);


        System.out.println(wishlistModel.toString());

        entityManager.persist(wishlistModel);
        return wishlistModel;
    }

    public CustomerModel getCustomerRefById(int customerId) {
        return entityManager.getReference(CustomerModel.class, customerId);
    }
    public ProductModel getProductRefById(int productId) {
        return entityManager.getReference(ProductModel.class, productId);
    }

    public WishlistModel deleteWishlistById (int id) {
        WishlistModel wishlistModel = getWishlistModelById(id);
        entityManager.remove(wishlistModel);
        return wishlistModel;
    }

    public WishlistModel deleteWishlistByCustomerIdAndProductId(int customerId, int productId) {
        WishlistModel wishlistModel = entityManager.createQuery("SELECT w from WishlistModel w where w.customerID.id = :customerId AND w.productID.id = :productId", WishlistModel.class)
                .setParameter("customerId", customerId)
                .setParameter("productId", productId)
                .getSingleResult();
        if (wishlistModel == null) {
            throw new IllegalArgumentException("Customer ID and Product ID not found");
        }
        entityManager.remove(wishlistModel);
        return wishlistModel;
    }
}