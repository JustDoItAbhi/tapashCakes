package com.tapash.product_cake.dto.request;

import com.tapash.entity.product.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CreateCakeProductRequestDto {
    private String cakeName;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
    private CakeType cakeType;
    private Dietary dietary;
    private String description;
    private int preparationTimeMinutes;
    private int shelfLifeHours;
    private Set<CakeVariantRequestDto> variants;
    private Set<CakeCategoryOnlyNameReqDto> category;
    private Set<IngredientRequestDto> ingredients = new HashSet<>();
    List<RatingRequestDto>ratings;
    private Set<String> tags;
}
