package Invoice.controller;

import Invoice.service.InvoiceService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("invoice")
public class InvoiceController {

    @Inject
    InvoiceService invoiceService;

    @POST
    @Path("createInvoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Object[]> createInvoice(@QueryParam("orderDetailsId") int orderDetailsId) {
        return invoiceService.createInvoice(orderDetailsId);
    }
}
