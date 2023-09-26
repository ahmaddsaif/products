package dev.saif.productservice.thirdpartyclients.productservice.fakestore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductsDto {
    private FakeStoreProductDto[] products;
    private Integer total;
    private Integer skip;
    private Integer limit;
}
