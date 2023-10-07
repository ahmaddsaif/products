package dev.saif.productservice.services;

import dev.saif.productservice.dtos.CategoryDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public List<CategoryDto> getCategories();
    public CategoryDto getCategoryByName(String name);
}
