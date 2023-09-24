package dev.saif.productservice.services;

import dev.saif.productservice.dtos.FakeStoreProductDto;
import dev.saif.productservice.dtos.FakeStoreProductsDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.ArrayList;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    String getProductByIdUrl = "https://dummyjson.com/products/{id}";
    String getAllProductsUrl = "https://dummyjson.com/products";
    String createProductUrl = "https://dummyjson.com/products/add";
    String deleteProductUrl = "https://dummyjson.com/products/{id}";
    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(getProductByIdUrl, FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        GenericProductDto product = new GenericProductDto();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImages(fakeStoreProductDto.getImages());
        product.setDescription(fakeStoreProductDto.getDescription());

        return product;
    }

    @Override
    public ArrayList<GenericProductDto> getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductsDto> response =
                restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductsDto.class);
        FakeStoreProductsDto fakeStoreProductsDto = response.getBody();

        ArrayList<GenericProductDto> products = new ArrayList<>();
//        GenericProductDto[] products = new GenericProductDto[fakeStoreProductsDto.length];
        for(int i=0; i<fakeStoreProductsDto.getProducts().length; i++) {
            GenericProductDto product = new GenericProductDto();
            product.setTitle(fakeStoreProductsDto.getProducts()[i].getTitle());
            product.setCategory(fakeStoreProductsDto.getProducts()[i].getCategory());
            product.setPrice(fakeStoreProductsDto.getProducts()[i].getPrice());
            product.setImages(fakeStoreProductsDto.getProducts()[i].getImages());
            product.setDescription(fakeStoreProductsDto.getProducts()[i].getDescription());

            products.add(product);
        }

        return products;
    }

    @Override
    public FakeStoreProductDto createProduct(GenericProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                createProductUrl, product, FakeStoreProductDto.class
        );
        return response.getBody();
    }

    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto) {
        return null;
    }

    @Override
    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(deleteProductUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }
}
