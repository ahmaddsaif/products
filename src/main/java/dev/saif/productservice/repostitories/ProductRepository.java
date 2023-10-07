package dev.saif.productservice.repostitories;

import dev.saif.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository
        extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM product p WHERE p.category.name = ?1")
    public List<Product> findByCategoryName(String categoryName);
}
