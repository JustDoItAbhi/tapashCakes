package com.tapash.product_cake.dto.response;

import lombok.Data;

import java.util.UUID;
@Data
public class RatingResponseDto {
    private UUID ratingId;
    private int stars;
    private String comment;
}
