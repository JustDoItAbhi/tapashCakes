package com.tapash.categoreis.category_service;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.categoreis.category_exceptions.CategoryMessageException;
import com.tapash.categoreis.category_exceptions.excpetions.CakeCategoryNotFound;
import com.tapash.categoreis.category_exceptions.excpetions.CategoryAlreadyExsists;
import com.tapash.categoreis.category_mapper.CategoryMapper;
import com.tapash.categoreis.category_repo.CategoryRepository;
import com.tapash.entity.CakeCategory;
import com.tapash.entity.CategoryStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto dto) {
        Optional<CakeCategory>cakeCategory=categoryRepository.findByCategoryName(dto.getCategoryName());
        if(cakeCategory.isPresent()){
                throw new CategoryAlreadyExsists("THIS CATEGORY ALREADY EXISTS "+ dto.getCategoryName());
        }
        CakeCategory category=new CakeCategory();
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        category.setStatus(CategoryStatus.AVAILABLE);
        categoryRepository.save(category);
        return CategoryMapper.fromEntiryCategory(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<CakeCategory>categories=categoryRepository.findAll();
        List<CategoryResponseDto>responseDtos=new ArrayList<>();
        for(CakeCategory category:categories){
            responseDtos.add(CategoryMapper.fromEntiryCategory(category));
        }

        return responseDtos;
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(UUID id, CategoryRequestDto dto) {
        CakeCategory oldcategory=categoryRepository.getReferenceById(id);
        if(oldcategory==null){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
        CakeCategory category=oldcategory;
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);

        return CategoryMapper.fromEntiryCategory(category);
    }

    @Override
    @Transactional
    public boolean deleteCategory(UUID id) {
        CakeCategory category=categoryRepository.getReferenceById(id);
        if(category==null){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
            categoryRepository.deleteById(id);
            return true;
    }

    @Override
    public CategoryResponseDto findById(UUID id) {
        CakeCategory category=categoryRepository.getReferenceById(id);
        if(category==null){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
        return CategoryMapper.fromEntiryCategory(category);
    }

    @Override
@Transactional
    public CategoryResponseDto changeAvailableStatus(UUID id, String status) {
        System.out.println("status "+status+"   status category "+ CategoryStatus.AVAILABLE+" and "+CategoryStatus.NOT_AVAILABLE );
        CakeCategory category=categoryRepository.getReferenceById(id);
        if(category==null){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
        if(!status.equalsIgnoreCase(CategoryStatus.AVAILABLE.toString()) && !status.equalsIgnoreCase(CategoryStatus.NOT_AVAILABLE.toString())){
            throw new IllegalArgumentException("Invalid status. Must be AVAILABLE or NOT_AVAILABLE, but was: " + status);
        }

        CakeCategory cake=category;
        if(cake.getStatus().equals(CategoryStatus.AVAILABLE) && status.equalsIgnoreCase(CategoryStatus.NOT_AVAILABLE.toString())){
            cake.setStatus(CategoryStatus.NOT_AVAILABLE);
        } else if (cake.getStatus().equals(CategoryStatus.NOT_AVAILABLE)&& status.equalsIgnoreCase(CategoryStatus.AVAILABLE.toString())) {
            cake.setStatus(CategoryStatus.AVAILABLE);
        }
        categoryRepository.save(cake);
        return CategoryMapper.fromEntiryCategory(cake);
    }
}
