package dev.saif.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String title;
    private String description;
    private String image;
    private double price;
    private CategoryDto category;
}
