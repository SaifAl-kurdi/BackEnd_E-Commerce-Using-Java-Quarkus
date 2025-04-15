package Category.repository;

import Category.model.CategoryModel;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CategoryRepository {

    @Inject
    EntityManager entityManager;

    public List<CategoryModel> getAllCategory() {
        List<CategoryModel> categoryModels = entityManager.createQuery("select c from CategoryModel c", CategoryModel.class).getResultList();
        return categoryModels;
    }

    public CategoryModel getCategoryById(int id) {
        return entityManager.find(CategoryModel.class, id);
    }

    public CategoryModel getCategoryByName(String name) {
        CategoryModel categoryModel = entityManager.createQuery("select c from CategoryModel c where c.name = :name", CategoryModel.class).setParameter("name", name).getSingleResult();
        return categoryModel;
    }

    public CategoryModel createCategory (String name) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName(name);
        entityManager.persist(categoryModel);
        return categoryModel;
    }

    public CategoryModel deleteCategoryById(int id) {
        CategoryModel categoryModel = entityManager.find(CategoryModel.class, id);
        entityManager.remove(categoryModel);
        return categoryModel;
    }

    public CategoryModel updateCategoryById(int id, String name) {
        CategoryModel categoryModel = entityManager.find(CategoryModel.class, id);
        categoryModel.setName(name);
        entityManager.merge(categoryModel);
        return categoryModel;
    }
}
