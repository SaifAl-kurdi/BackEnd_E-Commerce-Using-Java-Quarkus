package Product.service;

import Category.model.CategoryModel;
import Product.dto.ProductDTO;
import Product.model.ProductModel;
import Product.repository.ProductRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> findAll() {
        List<ProductModel> products = productRepository.findAll();
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductDTO findById(int id) {
        ProductModel productModel = productRepository.findById(id);
        return new ProductDTO(productModel);
    }

    public List<ProductDTO> findByName(String name) {
        List<ProductModel> products = productRepository.findByName(name);
        return products.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public List<ProductDTO> findByCategoryId(int categoryId) {
        List<ProductModel> productModels = productRepository.findByCategoryId(categoryId);
        return productModels.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public ProductDTO addProduct(String name, String SKU,String description, float price, int stock, int categoryId) {
        ProductModel productModel = productRepository.addProduct(name, SKU, description, price, stock, categoryId);
        return new ProductDTO(productModel);
    }

    public ProductDTO deleteById(int id) {
        ProductModel productModel = productRepository.deleteById(id);
        return new ProductDTO(productModel);
    }

    public ProductDTO updateById(int id, String name, String SKU, String description, float price, int stock, int categoryId) {
        ProductModel productModel = productRepository.findById(id);

        if (productModel == null) {
            throw new WebApplicationException("Product with ID " + id + " not found", 404);
        }

        if (name != null) productModel.setName(name);
        if (SKU != null) productModel.setSKU(SKU);
        if (description != null) productModel.setDescription(description);
        if (price != 0) productModel.setPrice(price);
        if (stock != 0) productModel.setStock(stock);

        if (categoryId != 0) {
            CategoryModel category = productRepository.getCategoryReferenceById(categoryId);
            productModel.setCategory(category);
        }

        ProductModel updatedProduct = productRepository.updateById(productModel);
        return new ProductDTO(updatedProduct);
    }
}

