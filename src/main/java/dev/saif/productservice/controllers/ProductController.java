package dev.saif.productservice.controllers;

import dev.saif.productservice.dtos.ProductDto;
import dev.saif.productservice.models.Product;
import dev.saif.productservice.services.SelfProductService;
import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.exceptions.NotFoundException;
import dev.saif.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public List<ProductDto> getAllProducts() {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
//        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public ProductDto deleteProductById(@PathVariable("id") UUID id) throws NotFoundException {
        return productService.deleteProduct(id);
    }
    @PostMapping()
    public ProductDto createProduct(@RequestBody ProductDto product){
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public ProductDto updateProductById(@RequestBody ProductDto productDto, @PathVariable("id") UUID id) throws NotFoundException{
        return productService.updateProduct(productDto, id);
    }

    @GetMapping("/getByCategory/{categoryName}")
    public List<ProductDto> getProductsByCategory(@PathVariable("categoryName") String categoryName) {
        return productService.getProductsByCategory(categoryName);
    }
}
