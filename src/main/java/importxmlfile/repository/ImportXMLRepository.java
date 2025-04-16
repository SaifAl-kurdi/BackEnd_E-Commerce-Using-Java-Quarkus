package importxmlfile.repository;

import Category.model.CategoryModel;
import Customer.model.CustomerModel;
import Product.model.ProductModel;
import importxmlfile.dto.categorydto.CategoryDTO;
import importxmlfile.dto.customerdto.CustomerDTO;
import importxmlfile.dto.productdto.ProductDTO;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.management.Query;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ImportXMLRepository {

    @Inject
    EntityManager entityManager;

    public void importXMLForCustomers(List<CustomerModel> CustomerModel) {
        for (int i = 0; CustomerModel.size() > i; i++) {
            entityManager.persist(CustomerModel.get(i));
            if (i == 50) {
                entityManager.flush();
                entityManager.clear();
            }
            System.out.println("importing Customer: " + CustomerModel.get(i).getFirstName() + " " +
                    CustomerModel.get(i).getLastName() + " " + CustomerModel.get(i).getEmail() + " " +
                    CustomerModel.get(i).getAddress() + " " + CustomerModel.get(i).getPhoneNumber());
        }
    }

    public void importXMLForCategories(List<CategoryModel> categoryModels) {
        for (int i = 0; categoryModels.size() > i; i++) {
            entityManager.persist(categoryModels.get(i));
            if (i == 50) {
                entityManager.flush();
                entityManager.clear();
            }
            System.out.println("importing Category: " + categoryModels.get(i).getName());
        }

    }

    public void importXMLForProducts(List<ProductModel> productModels) {
        for (int i = 0; productModels.size() > i; i++) {
            entityManager.persist(productModels.get(i));
            if (i == 50) {
                entityManager.flush();
                entityManager.clear();
            }
            System.out.println("importing Product: " + productModels.get(i).getName());
        }
    }


}
