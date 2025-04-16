package Category.dto;

import Category.model.CategoryModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;

    public CategoryDTO(CategoryModel category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}