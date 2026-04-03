package com.tapash.product_cake.dto.request;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CakeVariantRequestDto {
    private double weight;
    private double size;
    private BigDecimal price;
}
