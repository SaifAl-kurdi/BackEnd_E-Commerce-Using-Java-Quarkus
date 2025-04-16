package OrderDetails.dto;

import Category.dto.CategoryDTO;
import Customer.dto.CustomerDTO;
import OrderDetails.model.OrderDetailsModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class OrderDetailsDTO {

    private int id;
    private double totalPrice;
    private CategoryDTO customer;

    public OrderDetailsDTO(OrderDetailsModel orderDetails) {
        this.id = orderDetails.getId();
        this.totalPrice = orderDetails.getTotalPrice();

        if (orderDetails.getCustomer() != null) {
            CustomerDTO customer = new CustomerDTO(orderDetails.getCustomer());
        }
    }
}