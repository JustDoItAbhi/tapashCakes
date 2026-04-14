package com.tapash.product_cake.dto.response;

import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.entity.product.CakeAvailableEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
public class ProductCakesWIthCategoryandImage {
    private UUID cakeId;
    private String cakeName;
    private Set<CategoryResponseDto>categoryResponseDtos;
    private BigDecimal price;
    private int stock;
    private CakeAvailableEnum isAvailable;
    private Set<String> imageUrl;

}
