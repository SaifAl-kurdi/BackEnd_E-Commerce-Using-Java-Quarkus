package Invoice.service;

import Invoice.dto.InvoiceDTO;
import Invoice.repository.InvoiceRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class InvoiceService {

    @Inject
    InvoiceRepository invoiceRepository;

    public InvoiceDTO getInvoiceById(int invoiceId) {
        return new InvoiceDTO(invoiceRepository.getInvoiceModelById(invoiceId));
    }

    public InvoiceDTO getInvoiceByOrderDetailsIdId(int orderDetailsId) {
        return new InvoiceDTO(invoiceRepository.getInvoiceByOrderDetailsId(orderDetailsId));
    }

    public List<Object[]> createInvoice(int orderDetailsId) {
        return invoiceRepository.createInvoice(orderDetailsId);
    }

    public InvoiceDTO deleteInvoiceId(int id) {
        return new InvoiceDTO(invoiceRepository.deleteInvoiceId(id));
    }

    public InvoiceDTO deleteInvoiceByOrderDetailsId(int orderDetailsId) {
        return new InvoiceDTO(invoiceRepository.deleteInvoiceByOrderDetailsId(orderDetailsId));
    }

    public InvoiceDTO updateInvoiceById(int id, int orderDetailsId) {
        return new InvoiceDTO(invoiceRepository.updateInvoiceById(id, orderDetailsId));
    }

}
