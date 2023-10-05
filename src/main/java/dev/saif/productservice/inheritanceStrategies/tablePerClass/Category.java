package dev.saif.productservice.inheritanceStrategies.tablePerClass;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "tpc_category")
public class Category extends BaseModel {
    private String name;
    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
