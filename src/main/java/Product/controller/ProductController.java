package Product.controller;

import Product.dto.ProductDTO;
import Product.service.ProductService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("product")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @GET
    @Path("/getById")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ProductDTO getById(@QueryParam("id") int id) {
        return productService.findById(id);
    }

    @GET
    @Path("/getByName")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ProductDTO> getByName(@QueryParam("name") String name) {
        return productService.findByName(name);
    }

    @GET
    @Path("/getByCategoryId")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ProductDTO> getByCategoryId(@QueryParam("id") int id) {
        return productService.findByCategoryId(id);
    }

    @POST
    @Path("addProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ProductDTO addProduct(
            @QueryParam("name") String name,
            @QueryParam("SKU") String SKU,
            @QueryParam("description") String description,
            @QueryParam("price") float price,
            @QueryParam("stock") int stock,
            @QueryParam("categoryId") int categoryId) {

        return productService.addProduct(name, SKU, description, price, stock, categoryId);
    }

    @DELETE
    @Path("deleteById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ProductDTO deleteById(@QueryParam("id") int id) {
        return productService.deleteById(id);
    }

    @PUT
    @Path("updateById")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ProductDTO updateById(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("SKU") String SKU,
            @QueryParam("description") String description,
            @QueryParam("price") float price,
            @QueryParam("stock") int stock,
            @QueryParam("categoryId") int categoryId
    ) {
        return productService.updateById(id, name, SKU, description, price, stock, categoryId);
    }
}
