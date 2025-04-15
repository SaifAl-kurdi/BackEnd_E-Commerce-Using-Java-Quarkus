package Invoice.dto;

import Invoice.model.InvoiceModel;
import OrderDetails.dto.OrderDetailsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class InvoiceDTO {

    private int id;
    private OrderDetailsDTO orderDetailsDTO;

    public InvoiceDTO(InvoiceModel invoiceModel) {
        this.id = invoiceModel.getId();
        if (invoiceModel.getOrderDetailsModel() != null) {
            this.orderDetailsDTO = new OrderDetailsDTO(invoiceModel.getOrderDetailsModel());
        }
    }
}
