package importxmlfile.dto.productdto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDTO {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "SKU")
    private String SKU;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "Price")
    private float price;

    @XmlElement(name = "Stock")
    private int stock;

    @XmlElement(name = "Category_Id")
    private int categoryId;

    public String getNamed() {
        return name;
    }
    public void setNamed(String name) {
        this.name = name;
    }

    public String getSKUU() {
        return SKU;
    }
    public void getSKUU(String SKU) {
        this.SKU = SKU;
    }

    public String getDescriptionn() {
        return description;
    }
    public void setDescriptionn(String description) {
        this.description = description;
    }

    public float getPricee() {
        return price;
    }
    public void setPricee(float price) {
        this.price = price;
    }

    public int getStockk() {
        return stock;
    }
    public void setStockk(int stock) {
        this.stock = stock;
    }

    public int getCategoryIdd() {
        return categoryId;
    }
    public void setCategoryIdd(int categoryId) {
        this.categoryId = categoryId;
    }
}


