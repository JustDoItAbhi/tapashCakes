package com.tapash.product_cake.dto.response;

import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.entity.product.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Data
public class ProductCakeResponseDto {
    private UUID cakeId;
    private LocalDate createAtDate;
    private LocalTime createAtTime;
    private String cakeName;
    private String description;
    private BigDecimal price;
    private int stock;
    private CakeAvailableEnum isAvailable;
    private String imageUrl;
    private CakeType cakeType;
    private Set<CakeVariantResponseDto> variants;
    private Set<CategoryResponseDto> categories = new HashSet<>();
    private Set<IngredientResponseDto> ingredients = new HashSet<>();
    private Set<String> tags;
    private Set<DieteryResponseDto> dietary;
    Set<RatingResponseDto>ratings;
    private int preparationTimeMinutes;
    private int shelfLifeHours;// cake validity
}
