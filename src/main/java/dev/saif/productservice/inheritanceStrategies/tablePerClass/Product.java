package dev.saif.productservice.inheritanceStrategies.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tpc_product")
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
