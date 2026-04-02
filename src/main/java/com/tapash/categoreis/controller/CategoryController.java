package com.tapash.categoreis.controller;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.categoreis.category_dtos.ChangeStatusToNotAvailable;
import com.tapash.categoreis.category_service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> create(@RequestBody CategoryRequestDto dto){
        return ResponseEntity.ok(categoryService.createCategory(dto));
    }
    @GetMapping("/")
    public ResponseEntity<Page<CategoryResponseDto>> getAll(@RequestParam (required = false,defaultValue = "1")int pageNumber,
                                                            @RequestParam(required = false,defaultValue = "5")int pageSize,
                                                            @RequestParam(required = false,defaultValue = "status")String sortBy,
                                                            @RequestParam(required = false,defaultValue = "ASC") String sortedFrom){

        Sort sort=null;
        if(sortedFrom.equalsIgnoreCase("asc")){
            sort=sort.by(Sort.Direction.fromString(sortedFrom.toUpperCase()),sortBy);
        }else if(sortedFrom.equalsIgnoreCase("desc")){
            sort=sort.by(Sort.Direction.fromString(sortedFrom.toUpperCase()),sortBy);
        }
        PageRequest request=PageRequest.of(pageNumber-1,pageSize,sort);
        return ResponseEntity.ok(categoryService.getAllCategories(request));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<CategoryResponseDto> getbyid(@PathVariable("id")UUID id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDto> updatecategory(@PathVariable("id")UUID id,
                                                       @RequestBody CategoryRequestDto dto){
        return ResponseEntity.ok(categoryService.updateCategory(id,dto));
    }
    @PutMapping("/updateStatus")
    public ResponseEntity<CategoryResponseDto> changeStatusToNotAvailable(@RequestBody ChangeStatusToNotAvailable dto){
        return ResponseEntity.ok(categoryService.changeAvailableStatus(dto.getId(), dto.getStatus()));
    }


}
