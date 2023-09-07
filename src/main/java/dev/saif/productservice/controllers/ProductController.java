package dev.saif.productservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products/")
public class ProductController {
    @GetMapping()
    public void getAllProducts() {}
    @GetMapping("/{id}")
    public String getProductById(@PathVariable("id") String id){
        return "Here is product id: " + id;
    }
    @DeleteMapping("{id}")
    public void deleteProductById(){}
    @PostMapping()
    public void createProduct(){}
    @PutMapping("{id}")
    public void updateProductById(){}
}
