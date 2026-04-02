package com.tapash.categoreis.category_mapper;

import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.entity.CakeCategory;

import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public static   CategoryResponseDto fromEntiryCategory(CakeCategory cakeCategory){
        CategoryResponseDto dto=new CategoryResponseDto();
        dto.setId(cakeCategory.getId());
        dto.setCategoryName(cakeCategory.getCategoryName());
        dto.setStatus(cakeCategory.getStatus());
        dto.setCreatedDate(cakeCategory.getCreateAtDate());
        dto.setCreatedTime(cakeCategory.getCreateAtTime());
        dto.setDescription(cakeCategory.getDescription());
        return dto;
    }
}
