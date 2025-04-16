package OrderItems.repository;

import Invoice.repository.InvoiceRepository;
import OrderDetails.model.OrderDetailsModel;
import OrderItems.model.OrderItemsModel;
import Product.model.ProductModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrderItemsRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    InvoiceRepository invoiceRepository;

    public List<OrderItemsModel> getOrderItemsModel() {
        return entityManager.createQuery("SELECT w FROM OrderItemsModel w", OrderItemsModel.class).getResultList();
    }

    public OrderItemsModel getOrderItemsById(int id) {
        return entityManager.find(OrderItemsModel.class, id);
    }

    public List<Object[]> getOrderItemsModelByCustomerId(int CustomerId){
        List<Object[]> result =  entityManager.createQuery("SELECT c.id, c.firstName, c.lastName, c.phoneNumber, " +
                "p.id, p.name, p.description, p.price, cart.quantity " +
                "from CartModel cart " +
                "Join cart.customerModel c " +
                "Join cart.productModel p " +
                "where c.id = :CustomerId ", Object[].class).setParameter("CustomerId", CustomerId).getResultList();

        for (Object[] row : result) {
            int id = (Integer) row[0];
            String firstName = (String) row[1];
            String lastName = (String) row[2];
            String phoneNumber = (String) row[3];
            int productId = (Integer) row[4];
            String productName = (String) row[5];
            String description = (String) row[6];
            float price = (Float) row[7];
            int quantity = (Integer) row[8];
            System.out.println(id + " " + firstName + " " + lastName + " " + phoneNumber + " " + productName + " " + productId + " " + description + " " + price + " " + quantity);
        }
        return result;
    }

    // Here should I get the customer, product, and the order details of each product.
    public OrderItemsModel makeAnOrder(int quantity, int productId, int orderDetailsId) {
        OrderItemsModel orderItemsModel = new OrderItemsModel();
        orderItemsModel.setQuantity(quantity);
        if (productId != 0) {
            ProductModel productModel = getProductModelById(productId);
            orderItemsModel.setProduct(productModel);
        }
        if (orderDetailsId != 0) {
            OrderDetailsModel orderDetailsModel = getOrderDetailsById(orderDetailsId);
            orderItemsModel.setOrderDetails(orderDetailsModel);
        }
        entityManager.persist(orderItemsModel);
        changeOnProductQuantity(productId, quantity);
        invoiceRepository.createInvoice(orderDetailsId);

        // Get the customer Id then send it to the orderDetailsRepo, to make an order

        return orderItemsModel;
    }

    public ProductModel getProductModelById(int productId) {
        return entityManager.find(ProductModel.class, productId);
    }
    public OrderDetailsModel getOrderDetailsById(int orderId) {
        return entityManager.find(OrderDetailsModel.class, orderId);
    }


    // Should check on the product Id and customer id is the same, the quantity should not duplicate.


    // When they make a post to make an order, and the merge should be in the function when call it.
    // If here make an update order, it should calculate the update quantity is more than or less than
    // if more than should delete the addtions quantity.
    // But if the update quantity less than the order quantity should make a add on the quantity on the database.
    public int changeOnProductQuantity(int productId, int quantityItems) {
        System.out.println("Product id: " + productId + " Quantity: " + quantityItems);
        int Quantity = entityManager.createQuery("SELECT p.stock FROM ProductModel p WHERE p.id = :productId",
                Integer.class).setParameter("productId", productId).getSingleResult();

        System.out.println("Quantity: " + Quantity);

        System.out.println("------------------------------- Before changes -------------------------------");
        System.out.println("Quantity in the database " + Quantity + " And Quantity that the user wants " + quantityItems);
        System.out.println("------------------------------------------------------------------------------");

        if (Quantity < quantityItems) {
            System.out.println("There is no enough of the items in the database");
            return -1;
        }
        int  newQuantity = Quantity - quantityItems;

        ProductModel productModel = entityManager.find(ProductModel.class, productId);
        productModel.setStock(newQuantity);
        entityManager.merge(productModel);

        System.out.println("------------------------------- After changes -------------------------------");
        System.out.println("Quantity in the database " + newQuantity + " And Quantity that the user wants " + quantityItems);
        System.out.println("-----------------------------------------------------------------------------");

        return newQuantity;
    }

    public OrderItemsModel deleteOrderItemsById(int id) {
        OrderItemsModel orderItemsModel = getOrderItemsById(id);
        entityManager.remove(orderItemsModel);
        return orderItemsModel;
    }

    public List<OrderItemsModel> deleteOrderItemsByOrderDetailsId(int orderDetailsId) {
        List<Object> objects = entityManager.createQuery("SELECT oi from OrderItemsModel oi JOIN " +
                "OrderDetailsModel od ON oi.orderDetails.id = od.id where oi.orderDetails.id = :orderDetailsId", Object.class)
                .setParameter("orderDetailsId", orderDetailsId).getResultList();
        for (Object object : objects) {
            OrderDetailsModel orderDetailsModel = (OrderDetailsModel) object;
            entityManager.remove(object);
        }
        return objects.stream().map(OrderItemsModel.class::cast).collect(Collectors.toList());
    }

    public OrderItemsModel updateOrderItemsById(int id, int newQuantity, int productId, int orderDetailsId) {
        OrderItemsModel orderItemsModel = getOrderItemsById(id);

        int oldQuantity = entityManager.createQuery("Select oi.quantity from OrderItemsModel oi where oi.id = :id", Integer.class).setParameter("id", id).getSingleResult();

        System.out.println("New quantity: " + newQuantity  + " OldQuantity: " + oldQuantity);

        if (productId == 0) {
            productId = entityManager.createQuery("select p.id from OrderItemsModel oi JOIN ProductModel p " +
                    "on p.id = oi.product.id where oi.id = :id", Integer.class)
                    .setParameter("id", id).getSingleResult();
        }

        if (newQuantity > oldQuantity) {
            int newQuantities = newQuantity - oldQuantity;

            int newDatabaseReqOfQuantity = oldQuantity - newQuantities;

            System.out.println("New Database req of quantity: " + newDatabaseReqOfQuantity);

            orderItemsModel.setQuantity(newDatabaseReqOfQuantity);

            changeOnProductQuantity(productId, newDatabaseReqOfQuantity);
        } else if (newQuantity < oldQuantity) {

            int newQuantities = oldQuantity - newQuantity;

            int newDatabaseReqOfQuantity = oldQuantity + newQuantities;

            orderItemsModel.setQuantity(newDatabaseReqOfQuantity);

            changeOnProductQuantity(productId, newDatabaseReqOfQuantity);
        }

        if (productId != 0) {
            ProductModel productModel = getProductModelById(productId);
            orderItemsModel.setProduct(productModel);
        }
        if (orderDetailsId != 0) {
            OrderDetailsModel orderDetailsModel = getOrderDetailsById(orderDetailsId);
            orderItemsModel.setOrderDetails(orderDetailsModel);
        }
        entityManager.merge(orderItemsModel);
        return orderItemsModel;
    }
}