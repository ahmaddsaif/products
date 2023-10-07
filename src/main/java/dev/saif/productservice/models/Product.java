package dev.saif.productservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "product")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(generator = "uuidgenerator")
    @GenericGenerator(name = "uuidgenerator", strategy = "uuid2")
    @Column(name = "id", columnDefinition = "binary(16)", nullable = false, updatable = false)
    private UUID id;
    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Category category;
    private double price;
}
