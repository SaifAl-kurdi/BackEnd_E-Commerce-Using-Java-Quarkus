package OrderItems.dto;

import OrderDetails.dto.OrderDetailsDTO;
import OrderItems.model.OrderItemsModel;
import Product.dto.ProductDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class OrderItemsDTO {

    private int id;
    private int quantity;
    private ProductDTO product;
    private OrderDetailsDTO orderDetails;

    public OrderItemsDTO(OrderItemsModel orderItems) {
        this.id = orderItems.getId();
        this.quantity = orderItems.getQuantity();
        if (orderItems.getProduct() != null) {
            this.product = new ProductDTO(orderItems.getProduct());
        }
        if (orderItems.getOrderDetails() != null) {
            this.orderDetails = new OrderDetailsDTO(orderItems.getOrderDetails());
        }
    }
}