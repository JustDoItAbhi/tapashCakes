package com.tapash.product_cake.service;

import com.tapash.product_cake.dto.request.CreateCakeProductRequestDto;
import com.tapash.product_cake.dto.request.UpdateImages;
import com.tapash.product_cake.dto.response.ProductCakeResponseDto;
import com.tapash.product_cake.dto.response.ProductCakesWIthCategoryandImage;
import com.tapash.product_cake.dto.response.UpdateImageUrlsResponse;
import com.tapash.product_cake.dto.response.cakeinfo_response.CakeInfoResponseDto;

import java.util.List;
import java.util.UUID;

public interface ProductCakeService {
ProductCakeResponseDto createCake(CreateCakeProductRequestDto dto);
List<CakeInfoResponseDto> getAllProductCakes();
ProductCakeResponseDto updateCake(UUID id,CreateCakeProductRequestDto dto);
ProductCakeResponseDto getImageAndCategoryName(UUID id);
List<ProductCakesWIthCategoryandImage> getAllImages();
boolean deleteProduct(UUID id);
List<UpdateImageUrlsResponse>getImages();
UpdateImageUrlsResponse updateImageUrl(UpdateImages updateImages);
List<ProductCakeResponseDto> getAllProductbyCategory(UUID category);
}
