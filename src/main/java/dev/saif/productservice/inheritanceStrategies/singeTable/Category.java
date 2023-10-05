package dev.saif.productservice.inheritanceStrategies.singeTable;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "st_category")
@DiscriminatorValue("1")
public class Category extends BaseModel {
    private String name;
    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
