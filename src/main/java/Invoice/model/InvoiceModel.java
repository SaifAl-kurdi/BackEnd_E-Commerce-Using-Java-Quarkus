package Invoice.model;

import OrderDetails.model.OrderDetailsModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name = "Invoice")
@NoArgsConstructor
@Getter @Setter
@ToString
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderDetailsId", nullable = false)
    private OrderDetailsModel orderDetailsModel;
}