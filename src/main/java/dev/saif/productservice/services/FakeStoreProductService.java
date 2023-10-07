package dev.saif.productservice.services;

import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductDto;
import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductServiceClient;
import dev.saif.productservice.thirdpartyclients.productservice.fakestore.FakeStoreProductsDto;
import dev.saif.productservice.dtos.GenericProductDto;
import dev.saif.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Service("fakeStoreProductService")
public class FakeStoreProductService{//} implements ProductService{
    private FakeStoreProductServiceClient fakeStoreProductServiceClient;
    public FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }
//    @Override
    public GenericProductDto getProductById(UUID id) throws NotFoundException {
        try {
            return convertToGenericProductDto(fakeStoreProductServiceClient.getProductById(id));
        } catch (RestClientException e) {
            if(e.getMessage().contains("404"))
                throw new NotFoundException("Product with id: " + id + " does not exist.");
            throw new RuntimeException(e);
        }
    }

//    @Override
    public ArrayList<GenericProductDto> getProducts() {
        FakeStoreProductsDto fakeStoreProductsDto = fakeStoreProductServiceClient.getProducts();

        ArrayList<GenericProductDto> products = new ArrayList<>();
        for(int i = 0; i< Objects.requireNonNull(fakeStoreProductsDto).getProducts().length; i++) {
            products.add(convertToGenericProductDto(fakeStoreProductsDto.getProducts()[i]));
        }

        return products;
    }

//    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.createProduct(convertToFakeStoreProductDto(product)));
    }

//    @Override
    public GenericProductDto updateProduct(GenericProductDto genericProductDto, Long id) {
        return convertToGenericProductDto(
                fakeStoreProductServiceClient.updateProduct(
                        convertToFakeStoreProductDto(genericProductDto), id
                ));
    }

//    @Override
    public GenericProductDto deleteProduct(Long id) {
        return convertToGenericProductDto(fakeStoreProductServiceClient.deleteProduct(id));
    }

    public GenericProductDto convertToGenericProductDto(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImages(fakeStoreProductDto.getImages());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());

        return genericProductDto;
    }

    public FakeStoreProductDto convertToFakeStoreProductDto(GenericProductDto genericProductDto) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreProductDto.setImages(genericProductDto.getImages());
        fakeStoreProductDto.setTitle(genericProductDto.getTitle());
        fakeStoreProductDto.setPrice(genericProductDto.getPrice());
        fakeStoreProductDto.setCategory(genericProductDto.getCategory());

        return fakeStoreProductDto;
    }
}
