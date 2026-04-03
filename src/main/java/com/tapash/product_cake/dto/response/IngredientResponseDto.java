package com.tapash.product_cake.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class IngredientResponseDto {
    private UUID ingredientId;
    private String name;
    private String type;
}
