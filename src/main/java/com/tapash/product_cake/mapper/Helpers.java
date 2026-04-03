package com.tapash.product_cake.mapper;

import com.tapash.entity.category.CakeCategory;
import com.tapash.entity.product.CakeVariant;
import com.tapash.entity.product.Ingredient;
import com.tapash.entity.product.Rating;
import com.tapash.product_cake.dto.request.CakeCategoryOnlyNameReqDto;
import com.tapash.product_cake.dto.request.CakeVariantRequestDto;
import com.tapash.product_cake.dto.request.IngredientRequestDto;
import com.tapash.product_cake.dto.request.RatingRequestDto;

public class Helpers {
    public static CakeVariant convertToEntity(CakeVariantRequestDto dto) {
        CakeVariant variant = new CakeVariant();
        variant.setWeight(dto.getWeight());
        variant.setSize(dto.getSize());
        variant.setPrice(dto.getPrice());
        return variant;
    }

    public static Ingredient convertToIngregredientEntity(IngredientRequestDto dto){
        Ingredient ingredient=new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setPriceOfIngredient(ingredient.getPriceOfIngredient());
        ingredient.setType(dto.getType());
        return ingredient;
    }
    public static Rating convertToRatingEntity(RatingRequestDto dto){
        Rating rating=new Rating();
        rating.setStars(dto.getStars());
        rating.setComment(dto.getComment());
        rating.setProductCake(rating.getProductCake());
        return rating;
    }
    public static CakeCategory convertCategoryFromRequestProductCake(CakeCategoryOnlyNameReqDto dto){
        CakeCategory cakeCategory=new CakeCategory();
        cakeCategory.setCategoryName(dto.getName());
        return cakeCategory;
    }

}
