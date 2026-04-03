package com.tapash.categoreis.category_service;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.categoreis.category_dtos.FilterDto;
import com.tapash.categoreis.category_dtos.OnlyCakesTitlesAndId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
CategoryResponseDto createCategory(CategoryRequestDto dto);
Page<CategoryResponseDto> getAllCategories(Pageable request);
CategoryResponseDto updateCategory(UUID id, CategoryRequestDto dto);
boolean deleteCategory(UUID id);
CategoryResponseDto findById(UUID id);
CategoryResponseDto changeAvailableStatus(UUID id,String status);
    List<CategoryResponseDto> search(FilterDto dto);
    List<OnlyCakesTitlesAndId>ListOfCakeTitle(String title);
    CategoryResponseDto gtCakeByName(String name);
}
