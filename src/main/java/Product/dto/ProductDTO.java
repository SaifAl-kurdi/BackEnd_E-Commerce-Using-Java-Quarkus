package Product.dto;

import Category.dto.CategoryDTO;
import Product.model.ProductModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String name;
    private String SKU;
    private String description;
    private float price;
    private int stock;
    private CategoryDTO category;

    public ProductDTO(ProductModel product) {
        this.id = product.getId();
        this.name = product.getName();
        this.SKU = product.getSKU();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();

        if (product.getCategory() != null) {
            category = new CategoryDTO(product.getCategory());
        }
    }
}