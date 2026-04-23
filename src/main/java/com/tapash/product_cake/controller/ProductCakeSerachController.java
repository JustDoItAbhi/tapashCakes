package com.tapash.product_cake.controller;

import com.tapash.product_cake.dto.request.CreateCakeProductRequestDto;
import com.tapash.product_cake.dto.request.UpdateImages;
import com.tapash.product_cake.dto.response.ProductCakeResponseDto;
import com.tapash.product_cake.dto.response.ProductCakesWIthCategoryandImage;
import com.tapash.product_cake.dto.response.UpdateImageUrlsResponse;
import com.tapash.product_cake.dto.response.cakeinfo_response.CakeInfoResponseDto;
import com.tapash.product_cake.service.ProductCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cakes")
public class ProductCakeSerachController {
    @Autowired
    private ProductCakeService productCakeService;
    @GetMapping("/")
    public ResponseEntity<List<CakeInfoResponseDto>> getAllCakes() {

        return ResponseEntity.ok(productCakeService.getAllProductCakes());
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductCakeResponseDto> getChoosenById(@PathVariable("id")UUID id){
        return ResponseEntity.ok(productCakeService.getImageAndCategoryName(id));
    }
    @GetMapping("/product/all")
    public ResponseEntity<List<ProductCakesWIthCategoryandImage>> getChooesn(){
        return ResponseEntity.ok(productCakeService.getAllImages());
    }
    @GetMapping("/product/allPro")
    public ResponseEntity<List<UpdateImageUrlsResponse>> getAlImage(){
        return ResponseEntity.ok(productCakeService.getImages());
    }
    @PostMapping("/updateProdUrl")
    public ResponseEntity<UpdateImageUrlsResponse> updateImageUrl(@RequestBody UpdateImages updateImages){
        return ResponseEntity.ok(productCakeService.updateImageUrl(updateImages));
    }
    @GetMapping("/AllProductsByCategory/{category}")
    public ResponseEntity<List<ProductCakeResponseDto>> getAllProductsByCategory(
            @PathVariable("category")UUID category){

        return ResponseEntity.ok(productCakeService.getAllProductbyCategory(category));
    }


}
