package com.tapash.product_cake.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CakeVariantResponseDto {
    private double weight;
    private double size;
    private BigDecimal price;
}
