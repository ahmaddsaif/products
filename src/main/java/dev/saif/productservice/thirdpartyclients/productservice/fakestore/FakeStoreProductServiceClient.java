package dev.saif.productservice.thirdpartyclients.productservice.fakestore;

import dev.saif.productservice.exceptions.NotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Value("${fakeStore.base.url}")
    private String fakeStoreBaseUrl = "https://dummyjson.com";

    @Setter
    @Value("${fakeStore.products}")
    private String fakeStoreProducts = "/products";

    String productByIdUrl = fakeStoreBaseUrl + fakeStoreProducts + "/{id}";
    String getAllProductsUrl = fakeStoreBaseUrl + fakeStoreProducts;
    String createProductUrl = fakeStoreBaseUrl + fakeStoreProducts + "/add";
    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        try {
            RestTemplate restTemplate = restTemplateBuilder.build();
            ResponseEntity<FakeStoreProductDto> response =
                    restTemplate.getForEntity(productByIdUrl, FakeStoreProductDto.class, id);
            FakeStoreProductDto fakeStoreProductDto = response.getBody();
            return response.getBody();

        } catch (RestClientException e) {
            if(e.getMessage().contains("404"))
                throw new NotFoundException("Product with id: " + id + " does not exist.");
            throw new RuntimeException(e);
        }
    }

    
    public FakeStoreProductsDto getProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductsDto> response =
                restTemplate.getForEntity(getAllProductsUrl, FakeStoreProductsDto.class);
        return response.getBody();
    }

    
    public FakeStoreProductDto createProduct(FakeStoreProductDto product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                createProductUrl, product, FakeStoreProductDto.class
        );
        return response.getBody();
    }

    
    public FakeStoreProductDto updateProduct(FakeStoreProductDto fakeStoreProductDto, Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productByIdUrl, HttpMethod.PUT,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }

    
    public FakeStoreProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productByIdUrl, HttpMethod.DELETE,
                requestCallback, responseExtractor, id);

        return response.getBody();
    }
}
