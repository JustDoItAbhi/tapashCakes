package com.tapash.product_cake.dto.response.cakeinfo_response;

import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.entity.category.CakeCategory;
import com.tapash.entity.product.CakeAvailableEnum;
import com.tapash.product_cake.dto.request.CakeCategoryOnlyNameReqDto;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.UUID;
@Data
public class CakeInfoResponseDto {
    private UUID cakeId;
    private LocalDate createAtDate;
    private LocalTime createAtTime;
    private Set<CategoryResponseDto> categoryResponseDtos;
    private String cakeName;
    private BigDecimal price;
    private int stock;
    private CakeAvailableEnum isAvailable;
    private Set<String> imageUrl;
    private String description;
}
