package com.tapash.categoreis.category_dtos;

import com.tapash.entity.category.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDto {
    private UUID id;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private  String categoryName;
    private  String description;
  private CategoryStatus status;
}
