package Category.controller;

import Category.dto.CategoryDTO;
import Category.service.CategoryService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("category")
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @GET
    @Path("AllTheCategory")
    @Transactional
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GET
    @Path("CategoryById")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO getCategoryById(@QueryParam("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @GET
    @Path("CategoryByName")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO getCategoryByName(@QueryParam("name") String name) {
        return categoryService.getCategoryByName(name);
    }

    @POST
    @Path("createCategory")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO createCategory(@QueryParam("name") String name) {
        return categoryService.createCategory(name);
    }

    @DELETE
    @Path("deleteCategory")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO deleteCategoryById(@QueryParam("id") int id) {
        return categoryService.deleteCategoryById(id);
    }

    @PUT
    @Path("updateCategory")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public CategoryDTO updateCategory(
            @QueryParam("id") int id,
            @QueryParam("name") String name) {
        return categoryService.updateCategoryById(id, name);
    }


}
