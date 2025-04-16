package Wishlist.model;

import Customer.model.CustomerModel;
import Product.model.ProductModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "Wishlist")
@NoArgsConstructor
@Getter @Setter
@ToString
public class WishlistModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "productID")
    private ProductModel productID;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private CustomerModel customerID;
}