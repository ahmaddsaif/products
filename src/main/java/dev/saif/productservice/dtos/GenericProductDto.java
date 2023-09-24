package dev.saif.productservice.dtos;

import dev.saif.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private String title;
    private String description;
    private String[] images;
    private String category;
    private double price;
}
