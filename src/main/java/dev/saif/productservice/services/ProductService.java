package dev.saif.productservice.services;
import dev.saif.productservice.dtos.FakeStoreProductDto;
import dev.saif.productservice.dtos.FakeStoreProductsDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.models.Product;

import java.util.ArrayList;

public interface ProductService {
    public GenericProductDto getProductById(Long id);

    public ArrayList<GenericProductDto> getProducts();

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto);

    public GenericProductDto updateProduct(GenericProductDto genericProductDto);
    public FakeStoreProductDto deleteProduct(Long id);
}
