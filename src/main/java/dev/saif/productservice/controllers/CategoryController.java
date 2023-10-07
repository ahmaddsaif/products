package dev.saif.productservice.controllers;

import dev.saif.productservice.dtos.CategoryDto;
import dev.saif.productservice.dtos.ProductDto;
import dev.saif.productservice.models.Category;
import dev.saif.productservice.models.Product;
import dev.saif.productservice.repostitories.CategoryRepository;
import dev.saif.productservice.services.CategoryService;
import dev.saif.productservice.services.SelfCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        return categoryService.getCategories();
    }
}
