package com.tapash.product_cake.mapper;

import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.entity.category.CakeCategory;
import com.tapash.entity.product.*;
import com.tapash.product_cake.dto.request.*;
import com.tapash.product_cake.dto.response.*;
import com.tapash.product_cake.dto.response.cakeinfo_response.CakeInfoResponseDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductCakeManualMapper {

    // Convert Entity to Response DTO
    public static ProductCakeResponseDto toResponseDto(ProductCake product) {
        if (product == null) return null;

        ProductCakeResponseDto dto = new ProductCakeResponseDto();

        // Basic fields
        dto.setCakeId(product.getId());
        dto.setCreateAtDate(product.getCreateAtDate());
        dto.setCreateAtTime(product.getCreateAtTime());
        dto.setCakeName(product.getCakeName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
//        dto.setUniqueCode(String.valueOf(product.getUniqueCode())); // int to String
        dto.setIsAvailable(product.getIsAvailable());
        dto.setCakeType(product.getCakeType());
        dto.setPreparationTimeMinutes(product.getPreparationTimeMinutes());
        dto.setShelfLifeHours(product.getShelfLifeHours());

        // Handle imageUrls (Set<String> to single String)
        if (product.getImageUrls() != null && !product.getImageUrls().isEmpty()) {
            dto.setImageUrl(product.getImageUrls().iterator().next());
        }

        // Handle Dietary (single object)
        if (product.getDietary() != null) {
            Set<DieteryResponseDto> dietarySet = new HashSet<>();
            DieteryResponseDto dieteryDto = new DieteryResponseDto();
            dieteryDto.setEggless(product.getDietary().isEggless());
            dieteryDto.setVegan(product.getDietary().isVegan());
            dieteryDto.setGlutenFree(product.getDietary().isGlutenFree());
            dietarySet.add(dieteryDto);
            dto.setDietary(dietarySet);
        }

        // Handle Variants
        if (product.getVariants() != null && !product.getVariants().isEmpty()) {
            Set<CakeVariantResponseDto> variantDtos = new HashSet<>();
            for (CakeVariant variant : product.getVariants()) {
                CakeVariantResponseDto variantDto = new CakeVariantResponseDto();
                variantDto.setWeight(variant.getWeight());
                variantDto.setSize(variant.getSize());
                variantDto.setPrice(variant.getPrice());
                variantDtos.add(variantDto);
            }
            dto.setVariants(variantDtos);
        }

        // Handle Categories
        if (product.getCategories() != null && !product.getCategories().isEmpty()) {
            Set<CategoryResponseDto> categoryDtos = new HashSet<>();
            for (CakeCategory category : product.getCategories()) {
                CategoryResponseDto categoryDto = new CategoryResponseDto();
                categoryDto.setId(category.getId());
                categoryDto.setCategoryName(category.getCategoryName());
                categoryDto.setDescription(category.getDescription());
                categoryDto.setStatus(category.getStatus());
                categoryDto.setCreatedDate(category.getCreateAtDate());
                categoryDto.setCreatedTime(category.getCreateAtTime());
                categoryDtos.add(categoryDto);
            }
            dto.setCategories(categoryDtos);
        }

        // Handle Ingredients
        if (product.getIngredients() != null && !product.getIngredients().isEmpty()) {
            Set<IngredientResponseDto> ingredientDtos = new HashSet<>();
            for (Ingredient ingredient : product.getIngredients()) {
                IngredientResponseDto ingredientDto = new IngredientResponseDto();
                ingredientDto.setIngredientId (ingredient.getId());
                ingredientDto.setName(ingredient.getName());
                ingredientDto.setType(ingredient.getType());
                ingredientDtos.add(ingredientDto);
            }
            dto.setIngredients(ingredientDtos);
        }

        // Handle Ratings
        if (product.getRatings() != null && !product.getRatings().isEmpty()) {
            Set<RatingResponseDto> ratingDtos = new HashSet<>();
            for (Rating rating : product.getRatings()) {
                RatingResponseDto ratingDto = new RatingResponseDto();
                ratingDto.setRatingId(rating.getId());
                ratingDto.setStars(rating.getStars());
                ratingDto.setComment(rating.getComment());
                ratingDtos.add(ratingDto);
            }
            dto.setRatings(ratingDtos);
        }

        // Handle Tags
        if (product.getTags() != null && !product.getTags().isEmpty()) {
            dto.setTags(product.getTags());
        }

        return dto;
    }

    // Convert Request DTO to Entity
    public ProductCake toEntity(CreateCakeProductRequestDto dto) {
        if (dto == null) return null;

        ProductCake product = new ProductCake();

        // Basic fields
        product.setCakeName(dto.getCakeName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
//        product.setUniqueCode(Integer.parseInt(dto.getUniqueCode())); // String to int
        product.setDescription(dto.getDescription());
        product.setPreparationTimeMinutes(dto.getPreparationTimeMinutes());
        product.setShelfLifeHours(dto.getShelfLifeHours());
        product.setCakeType(dto.getCakeType());
        product.setIsAvailable(CakeAvailableEnum.IN_STOCK); // Default value

        // Handle imageUrl (String to Set<String>)
        if (dto.getImageUrl() != null && !dto.getImageUrl().isEmpty()) {
            Set<String> imageUrls = new HashSet<>();
            imageUrls.add(dto.getImageUrl());
            product.setImageUrls(imageUrls);
        }

        // Handle Dietary
        if (dto.getDietary() != null) {
            product.setDietary(dto.getDietary());
        }

        // Handle Variants
        if (dto.getVariants() != null && !dto.getVariants().isEmpty()) {
            Set<CakeVariant> variants = new HashSet<>();
            for (CakeVariantRequestDto variantDto : dto.getVariants()) {
                CakeVariant variant = new CakeVariant();
                variant.setWeight(variantDto.getWeight());
                variant.setSize(variantDto.getSize());
                variant.setPrice(variantDto.getPrice());
                variant.setProductCake(product); // Set parent reference
                variants.add(variant);
            }
            product.setVariants(variants);
        }

        // Handle Ingredients (just set IDs, you'll need to fetch from DB)
        if (dto.getIngredients() != null && !dto.getIngredients().isEmpty()) {
            Set<Ingredient> ingredients = new HashSet<>();
            for (IngredientRequestDto ingredientDto : dto.getIngredients()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientDto.getName());
                ingredient.setType(ingredientDto.getType());
                ingredients.add(ingredient);
            }
            product.setIngredients(ingredients);
        }

        // Handle Tags
        if (dto.getTags() != null && !dto.getTags().isEmpty()) {
            product.setTags(dto.getTags());
        }

        return product;
    }


    public static CakeInfoResponseDto toCakeInfoResponseDto(ProductCake products) {
        CakeInfoResponseDto dto = new CakeInfoResponseDto();
        dto.setCakeId(products.getId());
        dto.setCreateAtDate(products.getCreateAtDate());
        dto.setCreateAtTime(products.getCreateAtTime());
        dto.setCakeName(products.getCakeName());
        dto.setPrice(products.getPrice());
        dto.setStock(products.getStock());
        dto.setIsAvailable(products.getIsAvailable());
        dto.setDescription(products.getDescription());
        dto.setImageUrl(products.getImageUrls());
        Set<String> imageUrls = new HashSet<>();
        if (products.getImageUrls() != null && !products.getImageUrls().isEmpty()) {
            for(String url:products.getImageUrls()){
                imageUrls.add(url);
            }
            dto.setImageUrl(imageUrls);
        }

        return dto;
    }
//    public static ProductCake toCakeRequestUpdateBasicsIntoEntityProductCake(CreateCakeProductRequestDto products) {
//        ProductCake dto = new ProductCake();
//        dto.setCakeName(products.getCakeName());
//        dto.setPrice(products.getPrice());
//        dto.setStock(products.getStock());
//        dto.setIsAvailable(CakeAvailableEnum.IN_STOCK);
//        dto.setDescription(products.getDescription());
//        Set<String> imageUrls = new HashSet<>();
//        imageUrls.add(products.getImageUrl());
//        dto.setImageUrls(imageUrls);
//        return dto;
//    }





}