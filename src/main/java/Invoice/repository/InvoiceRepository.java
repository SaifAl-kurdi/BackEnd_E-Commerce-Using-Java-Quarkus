package Invoice.repository;

import Cart.model.CartModel;
import Customer.model.CustomerModel;
import Customer.repository.CRUDCustomerRepository;
import Invoice.model.InvoiceModel;
import OrderDetails.model.OrderDetailsModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class InvoiceRepository {

    @Inject
    EntityManager entityManager;

    public List<Object[]> createInvoice(int orderDetailsId) {
        int customerData = entityManager.createQuery("select c.id, o.id from OrderDetailsModel o JOIN CustomerModel c on " +
                "c.id = o.customer.id where o.id = :orderDetailsId", Integer.class)
                .setParameter("orderDetailsId", orderDetailsId).getSingleResult();

        int customerId = customerData;

        List<Object[]> invoiceItem = entityManager.createQuery("select p.name, p.description, " +
                            "p.price, cart.quantity, (p.price * cart.quantity) As lineTotal from CartModel cart " +
                            "join CustomerModel c On c.id = cart.customerModel.id " +
                            "join ProductModel p on p.id = cart.productModel.id where c.id = :customerId", Object[].class)
                            .setParameter("customerId", customerId).getResultList();

        InvoiceModel invoice = new InvoiceModel();
        OrderDetailsModel orderDetailsModel = getRefOfOrderDetails(orderDetailsId);
        if (orderDetailsModel != null) {
            invoice.setOrderDetailsModel(orderDetailsModel);
        }
        entityManager.persist(invoice);

        List<CartModel> cartModels = itemsDeleteFromCart(customerId);
        for (CartModel cartModel : cartModels) {
            System.out.println(cartModel.toString());
        }
        return invoiceItem;
    }

    public List<CartModel> itemsDeleteFromCart(int customerId) {
        List<CartModel> cartModels = entityManager.createQuery("Select cart From CartModel cart where customerModel.id = :customerId", CartModel.class).setParameter("customerId", customerId).getResultList();

        for (CartModel cartModel : cartModels) {
            cartModels.remove(cartModel);
        }
        return cartModels;
    }

    public OrderDetailsModel getRefOfOrderDetails(int orderDetailsId) {
        return entityManager.getReference(OrderDetailsModel.class, orderDetailsId);
    }
}
