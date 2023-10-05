package dev.saif.productservice.inheritanceStrategies.joinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "jt_product")
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "id")
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne
    private Category category;
    private double price;
}
