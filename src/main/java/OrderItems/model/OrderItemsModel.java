package OrderItems.model;

import OrderDetails.model.OrderDetailsModel;
import Product.model.ProductModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "OrderItems")
@NoArgsConstructor
@Getter @Setter
public class OrderItemsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "prodcutId", nullable = false)
    private ProductModel product;

    @ManyToOne
    @JoinColumn(name = "orderDetailsId", nullable = false)
    private OrderDetailsModel orderDetails;
}
