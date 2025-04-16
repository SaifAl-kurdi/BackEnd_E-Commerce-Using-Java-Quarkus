package Category.service;

import Category.dto.CategoryDTO;
import Category.model.CategoryModel;
import Category.repository.CategoryRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategory() {
        List<CategoryModel> categoryModel = categoryRepository.getAllCategory();
        return categoryModel.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(int id) {
        CategoryModel categoryModel = categoryRepository.getCategoryById(id);
        return new CategoryDTO(categoryModel);
    }

    public CategoryDTO getCategoryByName(String name) {
        CategoryModel categoryModel = categoryRepository.getCategoryByName(name);
        return new CategoryDTO(categoryModel);
    }

    public CategoryDTO createCategory(String name) {
        CategoryModel categoryModel = categoryRepository.createCategory(name);
        return new CategoryDTO(categoryModel);
    }

    public CategoryDTO deleteCategoryById(int id) {
        CategoryModel categoryModel = categoryRepository.deleteCategoryById(id);
        return new CategoryDTO(categoryModel);
    }

    public CategoryDTO updateCategoryById(int id, String name) {
        CategoryModel categoryModel = categoryRepository.updateCategoryById(id, name);
        return new CategoryDTO(categoryModel);
    }
}