package com.tapash.categoreis.category_service;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

public interface CategoryService {
CategoryResponseDto createCategory(CategoryRequestDto dto);
List<CategoryResponseDto> getAllCategories();
CategoryResponseDto updateCategory(UUID id, CategoryRequestDto dto);
boolean deleteCategory(UUID id);
CategoryResponseDto findById(UUID id);
CategoryResponseDto changeAvailableStatus(UUID id,String status);
}
