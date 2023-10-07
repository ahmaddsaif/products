package dev.saif.productservice.services;

import dev.saif.productservice.dtos.CategoryDto;
import dev.saif.productservice.models.Category;
import dev.saif.productservice.repostitories.CategoryRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfCategoryService")
@Primary
public class SelfCategoryService implements CategoryService {
    CategoryRepository categoryRepository;
    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<CategoryDto> getCategories() {
        List<Category> catogs = categoryRepository.findAll();
        List<CategoryDto> categories = new ArrayList<>();
        for(Category catog : catogs) {
            CategoryDto category = new CategoryDto();
            category.setName(catog.getName());
            categories.add(category);
        }
        return categories;
    }

    public CategoryDto getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
