package dev.saif.productservice.inheritanceStrategies.singeTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "st_product")
@DiscriminatorValue("2")
@Getter
@Setter
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private double price;
}
