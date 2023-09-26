package dev.saif.productservice.controllers;

import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.exceptions.NotFoundException;
import dev.saif.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController()
@RequestMapping("/products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public ArrayList<GenericProductDto> getAllProducts() {
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public ResponseEntity<GenericProductDto> getProductById(@PathVariable("id") Long id) throws NotFoundException {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
//        return productService.getProductById(id);
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
    public GenericProductDto updateProductById(@RequestBody GenericProductDto genericProductDto, @PathVariable("id") Long id){
        return productService.updateProduct(genericProductDto, id);

    }
}
