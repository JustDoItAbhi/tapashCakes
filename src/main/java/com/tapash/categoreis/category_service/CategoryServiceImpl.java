package com.tapash.categoreis.category_service;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.categoreis.category_dtos.FilterDto;
import com.tapash.categoreis.category_dtos.OnlyCakesTitlesAndId;
import com.tapash.global_exceptions.category_ex.CakeCategoryNotFound;
import com.tapash.global_exceptions.category_ex.CategoryAlreadyExsists;
import com.tapash.categoreis.category_mapper.CategoryMapper;
import com.tapash.categoreis.category_repo.CategoryRepository;
import com.tapash.categoreis.utils.SpecificationsUtils;
import com.tapash.entity.category.CakeCategory;
import com.tapash.entity.category.CategoryStatus;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    private SpecificationsUtils specificationsUtils;
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
    public Page<CategoryResponseDto> getAllCategories(Pageable request) {
        Page<CakeCategory>categories=categoryRepository.findAll(request);
        List<CategoryResponseDto>responseDtos=new ArrayList<>();
        for(CakeCategory category:categories){
            responseDtos.add(CategoryMapper.fromEntiryCategory(category));
        }

        return new PageImpl<>(responseDtos,request,categories.getTotalElements());
    }

    @Override
    @Transactional
    public CategoryResponseDto updateCategory(UUID id, CategoryRequestDto dto) {
        Optional<CakeCategory> oldcategory=categoryRepository.findById(id);
        if(oldcategory.isEmpty()){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
        CakeCategory category=oldcategory.get();
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);

        return CategoryMapper.fromEntiryCategory(category);
    }

    @Override
    @Transactional
    public boolean deleteCategory(UUID id) {
        Optional<CakeCategory> category=categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
            categoryRepository.deleteById(id);
            return true;
    }

    @Override
    public CategoryResponseDto findById(UUID id) {
        Optional<CakeCategory> category=categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CakeCategoryNotFound("THIS CAKE ID NOT FOUND "+id);
        }
        return CategoryMapper.fromEntiryCategory(category.get());
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

    @Override
    public List<CategoryResponseDto> search(FilterDto dto) {
    List<CakeCategory> category=    categoryRepository.findAll(SpecificationsUtils.search(dto));
    List<CategoryResponseDto>responseDtos=new ArrayList<>();
    for(CakeCategory cakeCategory:category){
        responseDtos.add(CategoryMapper.fromEntiryCategory(cakeCategory));
    }
        return responseDtos;
    }
    @Override
    public List<OnlyCakesTitlesAndId> ListOfCakeTitle(String title) {
        List<CakeCategory> titles=categoryRepository.findAll(SpecificationsUtils.searchListOfTitle(title));

        List<OnlyCakesTitlesAndId>response=new ArrayList<>();
        int count=1;
        for(CakeCategory cake :titles){
            OnlyCakesTitlesAndId cakesTitlesAndId1=new OnlyCakesTitlesAndId();
            cakesTitlesAndId1.setNumberOfCake(count++);
            cakesTitlesAndId1.setId(cake.getId());
            cakesTitlesAndId1.setTitle(cake.getCategoryName());
            response.add(cakesTitlesAndId1);
        }
        System.out.println("LIST OF CAKES "+ response.size());
        return response;
    }

    @Override
    public CategoryResponseDto gtCakeByName(String name) {
       Optional<CakeCategory> titles=categoryRepository.findOne(SpecificationsUtils.searchbyname(name));
        if(titles.isEmpty()){
            throw new CakeCategoryNotFound("no such cake exsists "+ name);
        }
        return CategoryMapper.fromEntiryCategory(titles.get());

    }

}
