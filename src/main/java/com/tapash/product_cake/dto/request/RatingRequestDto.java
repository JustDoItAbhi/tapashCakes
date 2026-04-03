package com.tapash.product_cake.dto.request;

import lombok.Data;

@Data
public class RatingRequestDto {
    private int stars;
    private String comment;
}
