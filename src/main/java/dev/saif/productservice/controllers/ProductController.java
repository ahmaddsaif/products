package dev.saif.productservice.controllers;

import dev.saif.productservice.dtos.FakeStoreProductDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.models.Product;
import dev.saif.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public ArrayList<GenericProductDto> getAllProducts() {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }
    @DeleteMapping("{id}")
    public FakeStoreProductDto deleteProductById(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
    @PostMapping()
    public FakeStoreProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }
    @PutMapping("{id}")
    public void updateProductById(@PathVariable("id") Long id){

    }
}
