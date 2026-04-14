package com.tapash.product_cake.service;

import com.tapash.categoreis.category_repo.CategoryRepository;
import com.tapash.entity.category.CakeCategory;
import com.tapash.entity.product.*;
import com.tapash.global_exceptions.category_ex.CakeCategoryNotFound;
import com.tapash.global_exceptions.product_ex.ProductCakeAlreadyExists;
import com.tapash.global_exceptions.product_ex.ProductCakeNotFound;
import com.tapash.product_cake.dto.request.*;
import com.tapash.product_cake.dto.response.ProductCakesWIthCategoryandImage;
import com.tapash.product_cake.dto.response.cakeinfo_response.CakeInfoResponseDto;
import com.tapash.product_cake.mapper.Helpers;
import com.tapash.product_cake.dto.response.ProductCakeResponseDto;
import com.tapash.product_cake.mapper.ProductCakeManualMapper;
import com.tapash.repository.IngredientRepository;
import com.tapash.repository.ProductCakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.IconUIResource;
import java.util.*;

@Service
@Transactional
public class ProductCakeServiceImpl implements ProductCakeService{
    private final ProductCakeRepository productCakeRepository;
    private final IngredientRepository ingredientRepository;
    private final CategoryRepository categoryRepository;



    public ProductCakeServiceImpl(ProductCakeRepository productCakeRepository,
                                  IngredientRepository ingredientRepository,
                                  CategoryRepository categoryRepository) {
        this.productCakeRepository = productCakeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository=categoryRepository;
    }
    @Value("${spring.backend.image.url}")
    private String backendUrl;

    @Override
    public ProductCakeResponseDto createCake(CreateCakeProductRequestDto dto) {
        Optional<ProductCake>exsistingCake=productCakeRepository.findByCakeName(dto.getCakeName());
        if(exsistingCake.isPresent()){
            throw new ProductCakeAlreadyExists("CAKE ALREADY EXSISTS "+dto.getCakeName());
        }
        ProductCake cake=new ProductCake();
        Set<CakeCategory> cakeCategorie = new HashSet<>();
        if (dto.getCategory() != null && !dto.getCategory().isEmpty()) {
            for (CakeCategoryOnlyNameReqDto categoryDto : dto.getCategory()) {
                String categoryName = categoryDto.getName();
                Optional<CakeCategory> category = categoryRepository.findByCategoryName(categoryName);
                if(category.isEmpty()) {
                    throw new CakeCategoryNotFound("Category not found: " + categoryName);
                }
                cakeCategorie.add(category.get());
            }
        }
        cake.setCategories(cakeCategorie);
        cake.setCakeName(dto.getCakeName());
        cake.setIsAvailable(CakeAvailableEnum.IN_STOCK);
        cake.setShelfLifeHours(dto.getShelfLifeHours());

        if(dto.getCakeType().equals(CakeType.BIRTHDAY)
                ||dto.getCakeType().equals(CakeType.CUSTOM)
                ||dto.getCakeType().equals(CakeType.REGULAR)
                ||dto.getCakeType().equals(CakeType.WEDDING ) ){
            cake.setCakeType(dto.getCakeType());
        }
        cake.setDietary(dto.getDietary());
        cake.setDescription(dto.getDescription());
        cake.setPrice(dto.getPrice());
        Set<String>images=new HashSet<>();
        images.add(backendUrl+"/"+dto.getImageUrl());
        System.out.println("IMAGE URL "+ images);
        cake.setImageUrls(images);
        cake.setPreparationTimeMinutes(dto.getPreparationTimeMinutes());
        cake.setStock(dto.getStock());

        Set<CakeVariant> cakeVariantSet = new HashSet<>();
        for(CakeVariantRequestDto variantDto : dto.getVariants()){
            CakeVariant variant = Helpers.convertToEntity(variantDto);

            variant.setProductCake(cake);
            cakeVariantSet.add(variant);
        }
        cake.setVariants(cakeVariantSet);
        if (dto.getIngredients() != null && !dto.getIngredients().isEmpty()) {
            Set<Ingredient> ingredientSet = new HashSet<>();
            for (IngredientRequestDto ingredientRequestDto : dto.getIngredients()) {
                Optional<Ingredient> existingIngredient = ingredientRepository.findByName(ingredientRequestDto.getName());
                if (existingIngredient.isPresent()) {
                    ingredientSet.add(existingIngredient.get());
                } else {
                    Ingredient newIngredient = Helpers.convertToIngregredientEntity(ingredientRequestDto);
                    ingredientSet.add(ingredientRepository.save(newIngredient));
                }
            }
            cake.setIngredients(ingredientSet);
        }

        if (dto.getRatings() != null && !dto.getRatings().isEmpty()) {
            Set<Rating> ratingSet = new HashSet<>();
            for (RatingRequestDto ratingRequestDto : dto.getRatings()) {
                Rating rating = Helpers.convertToRatingEntity(ratingRequestDto);
                rating.setProductCake(cake);
                ratingSet.add(rating);
            }
            cake.setRatings(ratingSet);
        }
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            cake.setTags(dto.getTags());
        }
       ProductCake productCake= productCakeRepository.save(cake);
        /*
           Hibernate.initialize(savedCake.getImageUrls());
    Hibernate.initialize(savedCake.getVariants());
    Hibernate.initialize(savedCake.getCategories());
    Hibernate.initialize(savedCake.getIngredients());
    Hibernate.initialize(savedCake.getRatings());
    Hibernate.initialize(savedCake.getTags()); // in case of lazy intializeation error will happend then run this
         */

        return ProductCakeManualMapper.toResponseDto(productCake);
    }

    @Override

    public List<CakeInfoResponseDto> getAllProductCakes() {
        List<ProductCake>productCakeList=productCakeRepository.findAll();
        List<CakeInfoResponseDto>responseDtos=new ArrayList<>();
        for(ProductCake cake:productCakeList){
            responseDtos.add(ProductCakeManualMapper.toCakeInfoResponseDto(cake));
        }

        return responseDtos;
    }

    @Override
    public ProductCakeResponseDto updateCake(UUID id, CreateCakeProductRequestDto dto) {
        Optional<ProductCake>exsistingCake=productCakeRepository.findById(id);
        if(exsistingCake.isEmpty()){
            throw new ProductCakeNotFound("CAKE NOT EXSISTS "+id);
        }
        ProductCake cake=exsistingCake.get();
        cake.setCakeName(dto.getCakeName());
        cake.setPrice(dto.getPrice());
        if(dto.getCakeType().equals(CakeType.BIRTHDAY)
                ||dto.getCakeType().equals(CakeType.CUSTOM)
                ||dto.getCakeType().equals(CakeType.REGULAR)
                ||dto.getCakeType().equals(CakeType.WEDDING ) ){
            cake.setCakeType(dto.getCakeType());
        }
        cake.setDietary(dto.getDietary());
        cake.setStock(dto.getStock());
        cake.setIsAvailable(CakeAvailableEnum.IN_STOCK);
        cake.setDescription(dto.getDescription());
        Set<String> imageUrls = new HashSet<>();
        imageUrls.add(dto.getImageUrl());
        cake.setImageUrls(imageUrls);
        if (dto.getRatings() != null && !dto.getRatings().isEmpty()) {
            Set<Rating> ratingSet = new HashSet<>();
            for (RatingRequestDto ratingRequestDto : dto.getRatings()) {
                Rating rating = Helpers.convertToRatingEntity(ratingRequestDto);
                rating.setProductCake(cake);
                ratingSet.add(rating);
            }
            cake.setRatings(ratingSet);
        }
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            cake.setTags(dto.getTags());
        }
        Set<CakeVariant> cakeVariantSet = new HashSet<>();
        for(CakeVariantRequestDto variantDto : dto.getVariants()){
            CakeVariant variant = Helpers.convertToEntity(variantDto);
            variant.setProductCake(cake);
            cakeVariantSet.add(variant);
        }
        cake.setVariants(cakeVariantSet);
        Set<CakeCategory> cakeCategorie = new HashSet<>();
        if (dto.getCategory() != null && !dto.getCategory().isEmpty()) {
            for (CakeCategoryOnlyNameReqDto categoryDto : dto.getCategory()) {
                String categoryName = categoryDto.getName();
                CakeCategory category = categoryRepository.findByCategoryName(categoryName)
                        .orElseThrow(() -> new CakeCategoryNotFound("Category not found: " + categoryName));
                cakeCategorie.add(category);
            }
        }
        if (dto.getIngredients() != null && !dto.getIngredients().isEmpty()) {
            Set<Ingredient> ingredientSet = new HashSet<>();
            for (IngredientRequestDto ingredientRequestDto : dto.getIngredients()) {
                Optional<Ingredient> existingIngredient = ingredientRepository.findByName(ingredientRequestDto.getName());
                if (existingIngredient.isPresent()) {
                    ingredientSet.add(existingIngredient.get());
                } else {
                    Ingredient newIngredient = Helpers.convertToIngregredientEntity(ingredientRequestDto);
                    ingredientSet.add(ingredientRepository.save(newIngredient));
                }
            }
            cake.setIngredients(ingredientSet);
        }

        return null;
    }

    @Override
    public ProductCakesWIthCategoryandImage getImageAndCategoryName(UUID id) {
        Optional<ProductCake>exsistingCake=productCakeRepository.findById(id);
        if(exsistingCake.isEmpty()){
            throw new ProductCakeNotFound("CAKE NOT EXSISTS "+id);
        }
        ProductCake cake=exsistingCake.get();
        return ProductCakeManualMapper.toCakeProductCategoryandiamge(cake);
    }

    @Override
    public List<ProductCakesWIthCategoryandImage> getAllImages() {
        List<ProductCake>productCakeList=productCakeRepository.findAll();
        List<ProductCakesWIthCategoryandImage>responseDtos=new ArrayList<>();
        for(ProductCake cake:productCakeList){
            responseDtos.add(ProductCakeManualMapper.toCakeProductCategoryandiamge(cake));
        }

        return responseDtos;
    }

    @Override
    public boolean deleteProduct(UUID id) {
        Optional<ProductCake>cake=productCakeRepository.findById(id);
        if(cake.isEmpty()){
            throw new ProductCakeNotFound("this cake not exissts "+id);
        }
        productCakeRepository.deleteById(id);
        return true;
    }

}
