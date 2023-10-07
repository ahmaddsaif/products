package dev.saif.productservice.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "category")
@PrimaryKeyJoinColumn(name = "id")
public class Category {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
