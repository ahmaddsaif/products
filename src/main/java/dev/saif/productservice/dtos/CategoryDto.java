package dev.saif.productservice.dtos;

import dev.saif.productservice.models.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private String name;
}
