package OrderDetails.repository;

import Cart.repository.CartRepository;
import Customer.model.CustomerModel;
import Invoice.model.InvoiceModel;
import OrderDetails.model.OrderDetailsModel;
import OrderItems.model.OrderItemsModel;
import OrderItems.repository.OrderItemsRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class OrderDetailsRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    OrderItemsRepository orderItemsRepository;

    @Inject
    CartRepository cartRepository;

    public List<Object[]> getOrderDetails() {
        List<Object[]> orderDetailsModel = entityManager.createQuery(
                "select c.id, c.firstName, c.lastName, c.phoneNumber, p.name, p.description, p.price, cart.quantity " +
                        "From CartModel cart JOIN cart.customerModel c JOIN cart.productModel p",
                Object[].class).getResultList();

        for (Object[] objects : orderDetailsModel) {
            int id = Integer.parseInt(objects[0].toString());
            String firstName = objects[1].toString();
            String lastName = objects[2].toString();
            String phoneNumber = objects[3].toString();
            String nameOfProduct = objects[4].toString();
            String description = objects[5].toString();
            float price = Float.parseFloat(objects[6].toString());
            int quantity = Integer.parseInt(objects[7].toString());

            System.out.println(id + " " + firstName + " " + lastName + " " + phoneNumber + " " + nameOfProduct + " " + description + " " + price + " " + quantity);
        }
        return orderDetailsModel;
    }

    public OrderDetailsModel getOrderDetailsByOrderId(int id) {
        return entityManager.find(OrderDetailsModel.class, id);
    }

    public OrderDetailsModel getOrderDetailsByCustomerId(int customerId) {
        return entityManager.createQuery("SELECT o From OrderDetailsModel o Join CustomerModel c On o.customer.id = c.id " +
                "where c.id = :customerId", OrderDetailsModel.class).setParameter("customerId", customerId).getSingleResult();
    }

    // Here should send list of data like product ID not just onse.
    public OrderDetailsModel createOrderDetails(int customerId) {
        OrderDetailsModel orderDetailsModel = new OrderDetailsModel();
        List<Object[]> orderDetails = entityManager.createQuery("select cart.quantity, p.price, cart.customerModel.id, " +
                        "p.id from CartModel cart Join " +
                        "CustomerModel c On cart.customerModel.id = c.id " +
                        "Join ProductModel p On cart.productModel.id = p.id where c.id = :customerId", Object[].class)
                .setParameter("customerId", customerId).getResultList();


        int productId = 0, quantity = 0, customerIdDatabase = 0;
        double totalPrice = 0;
        float price = 0;

        for (Object[] objects : orderDetails) {
            quantity = Integer.parseInt(objects[0].toString());
            price = Float.parseFloat(objects[1].toString());
            customerIdDatabase = Integer.parseInt(objects[2].toString());
            productId = Integer.parseInt(objects[3].toString());
            totalPrice += calculateTotalPrice(quantity, price);
        }

        if (customerId != 0 && customerId != -1) {
            CustomerModel customerModel = entityManager.find(CustomerModel.class, customerId);
            orderDetailsModel.setCustomer(customerModel);
            orderDetailsModel.setTotalPrice(totalPrice);
            entityManager.persist(orderDetailsModel);

            List<Integer> orderDetailsIds = entityManager.createQuery("SELECT o.id from OrderDetailsModel o " +
                            "Where o.customer.id = :customerIdDatabase",
                    Integer.class).setParameter("customerIdDatabase", customerIdDatabase).getResultList();


            List<Object[]> items = entityManager.createQuery("SELECT cart.quantity, cart.customerModel.id, cart.productModel.id " +
                    "from CartModel cart join CustomerModel cus on cus.id = cart.customerModel.id where cus.id = :customerId", Object[].class).setParameter("customerId", customerId).getResultList();


            for (Object[] objects : items) {
                int cartQuantity = Integer.parseInt(objects[0].toString());
                int customerIdData = Integer.parseInt(objects[1].toString());
                int productIdData = Integer.parseInt(objects[2].toString());

                for (int objects2 : orderDetailsIds) {
                    int orderDetailsId = objects2;
                    orderItemsRepository.makeAnOrder(quantity, productId, orderDetailsId);
                }
            }
            List<Integer> cartId = entityManager.createQuery("SELECT cart.id FROM CartModel cart JOIN CustomerModel c on c.id = cart.customerModel.id WHERE cart.customerModel.id = :customerId", Integer.class).setParameter("customerId", customerId).getResultList();
            for (Integer cart : cartId) {
                cartRepository.removeCart(cart);
            }
        } else {
            orderDetailsModel = updateOrderDetails(customerId, totalPrice);
        }
        return orderDetailsModel;
    }

    public OrderDetailsModel updateOrderDetails(int customerId, double price) {
        OrderDetailsModel orderDetailsModel = getOrderDetailsByCustomerId(customerId);
        orderDetailsModel.setTotalPrice(price);
        entityManager.merge(orderDetailsModel);
        return orderDetailsModel;
    }

    public double calculateTotalPrice(int quantity, float price) {
        double sum = 0;
        for (int i = 0; i < quantity; i++) {
            sum += price;
        }
        return sum;
    }

    public OrderDetailsModel deleteOrderDetails(int orderDetailId) {
        OrderDetailsModel orderDetailsModel = getOrderDetailsByOrderId(orderDetailId);

        List<Integer> orderItemsIds = entityManager.createQuery("SELECT oi.id from OrderItemsModel oi Join OrderDetailsModel od On od.id = oi.orderDetails.id " +
                "where oi.id = :orderDetailId", Integer.class).setParameter("orderDetailId", orderDetailId).getResultList();

        for (Integer orderItemId : orderItemsIds) {
            OrderItemsModel orderItem = entityManager.find(OrderItemsModel.class, orderItemId);
            if (orderItem != null) {
                entityManager.remove(orderItem);
            }
        }

        List<Integer> invoiceId = entityManager.createQuery("SELECT iv.id from InvoiceModel iv Join OrderDetailsModel od On od.id = iv.orderDetailsModel.id " +
                "where od.id = :orderDetailId", Integer.class).setParameter("orderDetailId", orderDetailId).getResultList();

        for (Integer invoiceId1 : invoiceId) {
            InvoiceModel invoice = entityManager.find(InvoiceModel.class, invoiceId1);
            if (invoice != null) {
                entityManager.remove(invoice);
            }
        }

        entityManager.remove(orderDetailsModel);
        return orderDetailsModel;
    }
}
