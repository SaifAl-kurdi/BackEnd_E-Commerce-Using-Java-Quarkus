package Invoice.repository;

import Cart.model.CartModel;
import Customer.model.CustomerModel;
import Customer.repository.CRUDCustomerRepository;
import Invoice.dto.InvoiceDTO;
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

    public InvoiceModel getInvoiceModelById(int invoiceId) {
        return entityManager.find(InvoiceModel.class, invoiceId);
    }

    public InvoiceModel getInvoiceByOrderDetailsId(int orderDetailsId) {
        Object invoice = entityManager.createQuery("SELECT o FROM InvoiceModel o WHERE o.orderDetailsModel.id = :orderDetailsId").setParameter("orderDetailsId", orderDetailsId).getSingleResult();
        return (InvoiceModel) invoice;
    }

    public List<Object[]> createInvoice(int orderDetailsId) {
        int customerData = entityManager.createQuery("select c.id, o.id from OrderDetailsModel o JOIN CustomerModel c on " +
                "c.id = o.customer.id where o.id = :orderDetailsId", Integer.class)
                .setParameter("orderDetailsId", orderDetailsId).getSingleResult();

        List<Object[]> invoiceItem = entityManager.createQuery("select p.name, p.description, " +
                            "p.price, cart.quantity, (p.price * cart.quantity) As lineTotal from CartModel cart " +
                            "join CustomerModel c On c.id = cart.customerModel.id " +
                            "join ProductModel p on p.id = cart.productModel.id where c.id = :customerData", Object[].class)
                            .setParameter("customerData", customerData).getResultList();

        InvoiceModel invoice = new InvoiceModel();
        OrderDetailsModel orderDetailsModel = getRefOfOrderDetails(orderDetailsId);
        if (orderDetailsModel != null) {
            invoice.setOrderDetailsModel(orderDetailsModel);
        }
        entityManager.persist(invoice);

        List<CartModel> cartModels = itemsDeleteFromCart(customerData);
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

    public InvoiceModel deleteInvoiceId(int id) {
        InvoiceModel invoiceModel = entityManager.find(InvoiceModel.class, id);
        entityManager.remove(invoiceModel);
        return invoiceModel;
    }

    public InvoiceModel deleteInvoiceByOrderDetailsId(int orderDetailsId) {
        InvoiceModel invoiceModel = entityManager.createQuery("SELECT i from InvoiceModel i where " +
                "orderDetailsModel.id = :orderDetailsId ", InvoiceModel.class)
                .setParameter("orderDetailsId", orderDetailsId).getSingleResult();
        return invoiceModel;
    }

    public InvoiceModel updateInvoiceById(int id, int orderDetailsId) {
        InvoiceModel invoiceModel = entityManager.find(InvoiceModel.class, id);
        if (orderDetailsId != 0) {
            OrderDetailsModel orderDetailsModel = entityManager.find(OrderDetailsModel.class, orderDetailsId);
            invoiceModel.setOrderDetailsModel(orderDetailsModel);
        }
        return invoiceModel;
    }
}
