package dev.saif.productservice.inheritanceStrategies.joinedTable;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "jt_category")
@PrimaryKeyJoinColumn(name = "id")
public class Category extends BaseModel {
    private String name;
    @OneToMany(fetch = jakarta.persistence.FetchType.EAGER, mappedBy = "category")
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
}
