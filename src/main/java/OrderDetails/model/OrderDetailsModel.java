package OrderDetails.model;

import Customer.model.CustomerModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "OrderDetails")
@NoArgsConstructor
@Getter @Setter
public class OrderDetailsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "total", nullable = false)
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerModel customer;
}
