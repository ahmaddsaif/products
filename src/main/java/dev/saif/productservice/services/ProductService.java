package dev.saif.productservice.services;
import dev.saif.productservice.dtos.ProductDto;
import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface ProductService {
    public ProductDto getProductById(UUID id) throws NotFoundException;

    public List<ProductDto> getProducts();

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto updateProduct(ProductDto productDto, UUID id) throws NotFoundException;
    public ProductDto deleteProduct(UUID id) throws NotFoundException;

    public List<ProductDto> getProductsByCategory(String categoryName);
}
