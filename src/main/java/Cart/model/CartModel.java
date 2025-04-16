package Cart.model;

import Customer.model.CustomerModel;
import Product.model.ProductModel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "Cart")
@Getter @Setter
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductModel productModel;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private CustomerModel customerModel;
}