package com.tapash.product_cake.controller;

import com.tapash.entity.product.ProductCake;
import com.tapash.product_cake.dto.request.CreateCakeProductRequestDto;
import com.tapash.product_cake.dto.response.ProductCakeResponseDto;
import com.tapash.product_cake.dto.response.ProductCakesWIthCategoryandImage;
import com.tapash.product_cake.dto.response.cakeinfo_response.CakeInfoResponseDto;
import com.tapash.product_cake.service.ProductCakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cakes")
public class ProductCakeController {

    @Autowired
    private ProductCakeService productCakeService;

    @PostMapping("/create")
    public ResponseEntity<ProductCakeResponseDto> createCake(@RequestBody CreateCakeProductRequestDto request) {

        return ResponseEntity.ok(productCakeService.createCake(request));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductCakeResponseDto> updateFullCake(@PathVariable("id")UUID id,
                                                                 @RequestBody CreateCakeProductRequestDto dto){
        return ResponseEntity.ok(productCakeService.updateCake(id,dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> updateFullCake(@PathVariable("id")UUID id){
        return ResponseEntity.ok(productCakeService.deleteProduct(id));
    }


}