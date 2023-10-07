package dev.saif.productservice.repostitories;

import dev.saif.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository
    extends JpaRepository<Category, UUID> {
    @Query(nativeQuery = true, value = "SELECT * FROM category WHERE name = ?1")
    Category findByName(String name);
}
