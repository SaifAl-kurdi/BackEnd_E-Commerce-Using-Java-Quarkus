package importxmlfile.service;

import Category.model.CategoryModel;
import Customer.model.CustomerModel;
import Product.model.ProductModel;
import importxmlfile.dto.categorydto.CategoriesDTO;
import importxmlfile.dto.categorydto.CategoryDTO;
import importxmlfile.dto.customerdto.CustomerDTO;
import importxmlfile.dto.customerdto.CustomersDTO;
import importxmlfile.dto.productdto.ProductDTO;
import importxmlfile.dto.productdto.ProductsDTO;
import importxmlfile.repository.ImportXMLRepository;
import importxmlfile.template.TemplateDesignPattern;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ImportXMLService extends TemplateDesignPattern {

    @Inject
    ImportXMLRepository importXMLRepository;

    @Override
    public Object init(byte[] file, String field) throws Exception {
        if (field.equalsIgnoreCase("Customers") || field.equalsIgnoreCase("Customer")) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomersDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object unmarshalled = unmarshaller.unmarshal(inputStream);
            return (CustomersDTO) unmarshalled;
        } else if (field.equalsIgnoreCase("Categories") || field.equalsIgnoreCase("Category")) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(CategoriesDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Object unmarshalled = unmarshaller.unmarshal(inputStream);
            return (CategoriesDTO) unmarshalled;
        } else if (field.equalsIgnoreCase("Products") || field.equalsIgnoreCase("Product")) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductsDTO.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (ProductsDTO) unmarshaller.unmarshal(inputStream);
        }
        return null;
    }

    @Override
    public void importXML(Object object) throws Exception {
        if (object instanceof CustomersDTO) {
            List<CustomerModel> customerModels = getCustomerModels((CustomersDTO) object);
            importXMLRepository.importXMLForCustomers(customerModels);
        } else if (object instanceof CategoriesDTO) {
            List<CategoryModel> categoryModel = getCategoryModels((CategoriesDTO) object);
            importXMLRepository.importXMLForCategories(categoryModel);
        } else if (object instanceof ProductsDTO) {
            List<ProductModel> productModels = getProductModels((ProductsDTO) object);
            importXMLRepository.importXMLForProducts(productModels);
        }
    }

    private static List<CustomerModel> getCustomerModels(CustomersDTO object) {
        List<CustomerModel> customerModels = new ArrayList<CustomerModel>();
        for (CustomerDTO customerDTO : object.getCustomer()) {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setFirstName(customerDTO.getFirstNameDTO());
            customerModel.setLastName(customerDTO.getLastNameDTO());
            customerModel.setEmail(customerDTO.getEmailDTO());
            customerModel.setAddress(customerDTO.getAddressDTO());
            customerModel.setPhoneNumber(String.valueOf(customerDTO.getPhoneNumberDTO()));
            customerModels.add(customerModel);
        }
        return customerModels;
    }

    private static List<CategoryModel> getCategoryModels(CategoriesDTO object) {
        List<CategoryModel> categoryModel = new ArrayList<CategoryModel>();
        for (CategoryDTO categoryDTO : object.getCategoryDTOList()) {
            CategoryModel categoryModel1 = new CategoryModel();
            categoryModel1.setName(categoryDTO.getName());
            categoryModel.add(categoryModel1);
        }
        return categoryModel;
    }

    private static List<ProductModel> getProductModels(ProductsDTO object) {
        List<ProductModel> productModel = new ArrayList<ProductModel>();
        for (ProductDTO productDTO : object.getProductDTO()) {
            ProductModel productModels = new ProductModel();
            productModels.setName(productDTO.getNamed());
            productModels.setSKU(productDTO.getSKUU());
            productModels.setDescription(productDTO.getDescriptionn());
            productModels.setPrice(productDTO.getPricee());
            productModels.setStock(productDTO.getStockk());
            CategoryModel category = new CategoryModel();
            category.setId(productDTO.getCategoryIdd());
            productModels.setCategory(category);
            productModel.add(productModels);
        }
        return productModel;
    }

    /*@Override
    public List<Object> saveToDatabase(List<Object> objects) throws Exception {
        if (objects instanceof CustomersDTO) {
            importXMLRepository.importXMLForCustomers(objects);

        }
    }*/
}



