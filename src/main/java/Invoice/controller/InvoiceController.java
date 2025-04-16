package Invoice.controller;

import Invoice.dto.InvoiceDTO;
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

    @GET
    @Path("getInvoiceById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public InvoiceDTO getInvoiceById(@QueryParam("invoiceId") int invoiceId) {
        return invoiceService.getInvoiceById(invoiceId);
    }

    @GET
    @Path("getInvoiceByOrderDetId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public InvoiceDTO getInvoiceByOrderDetailsIdId(@QueryParam("orderDetailsId") int orderDetailsId) {
        return invoiceService.getInvoiceByOrderDetailsIdId(orderDetailsId);
    }

    @POST
    @Path("createInvoice")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Object[]> createInvoice(@QueryParam("orderDetailsId") int orderDetailsId) {
        return invoiceService.createInvoice(orderDetailsId);
    }

    @DELETE
    @Path("deleteInvoiceById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public InvoiceDTO deleteInvoiceId(@QueryParam("id") int id) {
        return invoiceService.deleteInvoiceId(id);
    }

    @DELETE
    @Path("deleteInvoiceByOrderDetId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public InvoiceDTO deleteInvoiceByOrderDetailsId(@QueryParam("orderDetailsId") int orderDetailsId) {
        return invoiceService.deleteInvoiceByOrderDetailsId(orderDetailsId);
    }

    @PUT
    @Path("updateInvoiceId")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public InvoiceDTO updateInvoiceById(@QueryParam("id") int id,
                                        @QueryParam("orderDetailsId") int orderDetailsId) {
        return invoiceService.updateInvoiceById(id, orderDetailsId);
    }
}