package dev.saif.productservice;

import dev.saif.productservice.models.Category;
import dev.saif.productservice.models.Product;
import dev.saif.productservice.repostitories.CategoryRepository;
import dev.saif.productservice.repostitories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductserviceApplication(ProductRepository productRepository,
                                     CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductserviceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//            Category category = new Category();
//            category.setName("Category 1");
//            categoryRepository.save(category);
//
//            Product product = new Product();
//            product.setTitle("Product 1");
//            product.setDescription("Product 1 Description");
//            product.setPrice(100);
//            product.setCategory(category);
//            productRepository.save(product);

            Category category = categoryRepository.findById(UUID.fromString("b3cab642-84fc-4014-9fda-602a51c2d87f")).get();
            System.out.println(category.getName());

            for(Product p: category.getProducts()){
                System.out.println(p.getTitle());
            }
        }
}
