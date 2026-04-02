package com.tapash.categoreis.category_dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
public class CategoryRequestDto {
    @Valid
    @NotBlank(message = "category name should not be blank")
    private  String categoryName;
    @Length(max = 2000, message = "Description cannot exceed 2000 characters")
    private  String description;
}


