package dev.saif.productservice.services;

import dev.saif.productservice.dtos.CategoryDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.dtos.ProductDto;
import dev.saif.productservice.exceptions.NotFoundException;
import dev.saif.productservice.models.Category;
import dev.saif.productservice.models.Product;
import dev.saif.productservice.repostitories.CategoryRepository;
import dev.saif.productservice.repostitories.ProductRepository;
import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("selfProductService")
@Primary
public class SelfProductService implements ProductService {
    CategoryRepository categoryRepository;
    ProductRepository productRepository;
    public SelfProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        super();
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public ProductDto getProductById(UUID id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id: " + id + " does not exist."));
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(product.getCategory().getName());
        productDto.setCategory(categoryDto);
        return productDto;
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategory(categoryDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryRepository.findByName(
                productDto.getCategory().getName());
        if(category == null) {
            category = new Category();
            category.setName(productDto.getCategory().getName());
            category = categoryRepository.save(category);
        }
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        Product createdProduct = productRepository.save(product);

        ProductDto createdProductDto = new ProductDto();
        createdProductDto.setTitle(createdProduct.getTitle());
        createdProductDto.setDescription(createdProduct.getDescription());
        createdProductDto.setImage(createdProduct.getImage());
        createdProductDto.setPrice(createdProduct.getPrice());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(createdProduct.getCategory().getName());
        createdProductDto.setCategory(categoryDto);
        return createdProductDto;
    }

    @Override
    public ProductDto updateProduct(ProductDto product, UUID id) throws NotFoundException {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id: " + id + " does not exist."));
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setCategory(categoryRepository.findByName(product.getCategory().getName()));
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setImage(product.getImage());
        Product updatedProduct = productRepository.save(productToUpdate);

        ProductDto updatedProductDto = new ProductDto();
        updatedProductDto.setTitle(updatedProduct.getTitle());
        updatedProductDto.setDescription(updatedProduct.getDescription());
        updatedProductDto.setImage(updatedProduct.getImage());
        updatedProductDto.setPrice(updatedProduct.getPrice());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(updatedProduct.getCategory().getName());
        updatedProductDto.setCategory(categoryDto);
        return updatedProductDto;

    }

    public ProductDto deleteProduct(UUID id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product with id: " + id + " does not exist."));
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(product.getCategory().getName());
        productDto.setCategory(categoryDto);

        productRepository.deleteById(id);
        return productDto;
    }

    @Override
    public List<ProductDto> getProductsByCategory(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products) {
            ProductDto productDto = new ProductDto();
            productDto.setTitle(product.getTitle());
            productDto.setDescription(product.getDescription());
            productDto.setImage(product.getImage());
            productDto.setPrice(product.getPrice());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setName(product.getCategory().getName());
            productDto.setCategory(categoryDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }
}
