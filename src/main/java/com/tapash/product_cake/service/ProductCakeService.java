package com.tapash.product_cake.service;

import com.tapash.product_cake.dto.request.CreateCakeProductRequestDto;
import com.tapash.product_cake.dto.response.ProductCakeResponseDto;
import com.tapash.product_cake.dto.response.cakeinfo_response.CakeInfoResponseDto;

import java.util.List;

public interface ProductCakeService {
ProductCakeResponseDto createCake(CreateCakeProductRequestDto dto);
List<CakeInfoResponseDto> getAllProductCakes();
}
