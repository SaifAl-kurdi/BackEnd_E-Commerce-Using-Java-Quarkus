package Invoice.service;

import Invoice.repository.InvoiceRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class InvoiceService {

    @Inject
    InvoiceRepository invoiceRepository;

    public List<Object[]> createInvoice(int orderDetailsId) {
        return invoiceRepository.createInvoice(orderDetailsId);
    }

}
