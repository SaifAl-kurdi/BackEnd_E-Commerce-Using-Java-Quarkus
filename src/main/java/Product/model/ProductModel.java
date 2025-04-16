package Product.model;

import Category.model.CategoryModel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "Product")
@Getter @Setter
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String SKU;
    private String description;
    private float price;
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private CategoryModel category;
}