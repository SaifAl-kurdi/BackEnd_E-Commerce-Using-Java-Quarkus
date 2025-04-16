package Product.repository;

import Category.model.CategoryModel;
import Product.model.ProductModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProductRepository {
    @Inject
    EntityManager entityManager;

    public List<ProductModel> findAll() {
        return entityManager.createQuery("SELECT p FROM ProductModel p", ProductModel.class).getResultList();
    }

    public ProductModel findById(int id) {
        return entityManager.find(ProductModel.class, id);
    }

    public List<ProductModel> findByName(String name) {
        return entityManager.createQuery("SELECT p FROM ProductModel p WHERE p.name = :name", ProductModel.class).setParameter("name", name).getResultList();
    }

    public List<ProductModel> findByCategoryId(int categoryId) {
        return entityManager.createQuery("SELECT p FROM ProductModel p WHERE p.category.id = :categoryId", ProductModel.class).setParameter("categoryId", categoryId).getResultList();
    }

    public ProductModel addProduct(String name, String SKU,String description, float price, int stock, int categoryId) {
        ProductModel productModel = new ProductModel();
        productModel.setName(name);
        productModel.setSKU(SKU);
        productModel.setDescription(description);
        productModel.setPrice(price);
        productModel.setStock(stock);

        CategoryModel categoryModel =entityManager.getReference(CategoryModel.class, categoryId);
        productModel.setCategory(categoryModel);

        entityManager.persist(productModel);
        return productModel;
    }

    public ProductModel deleteById(int id) {
        ProductModel productModel = findById(id);
        entityManager.remove(productModel);
        return productModel;
    }

    public ProductModel updateById(ProductModel productModel) {
        entityManager.merge(productModel);
        return productModel;
    }
    public CategoryModel getCategoryReferenceById(int categoryId) {
        return entityManager.getReference(CategoryModel.class, categoryId);
    }
}