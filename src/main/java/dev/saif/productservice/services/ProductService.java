package dev.saif.productservice.services;
import dev.saif.productservice.dtos.FakeStoreProductDto;
import dev.saif.productservice.dtos.FakeStoreProductsDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.exceptions.NotFoundException;
import dev.saif.productservice.models.Product;

import java.util.ArrayList;

public interface ProductService {
    public GenericProductDto getProductById(Long id) throws NotFoundException;

    public ArrayList<GenericProductDto> getProducts();

    public FakeStoreProductDto createProduct(GenericProductDto genericProductDto);

    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id);
    public FakeStoreProductDto deleteProduct(Long id);
}
