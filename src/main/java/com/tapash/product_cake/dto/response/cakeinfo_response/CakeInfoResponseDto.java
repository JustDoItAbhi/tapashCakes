package com.tapash.product_cake.dto.response.cakeinfo_response;

import com.tapash.entity.product.CakeAvailableEnum;
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
    private String cakeName;
    private BigDecimal price;
    private int stock;
    private CakeAvailableEnum isAvailable;
    private Set<String> imageUrl;
    private String description;
}
