package com.tapash.categoreis.controller;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_dtos.CategoryResponseDto;
import com.tapash.categoreis.category_service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CatController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(required = false, defaultValue = "1") int pageNumber,
                        @RequestParam(required = false, defaultValue = "5") int pageSize,
                        @RequestParam(required = false, defaultValue = "status") String sortBy,
                        @RequestParam(required = false, defaultValue = "ASC") String sortDirection) {

        // Validate page number
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        // Validate page size
        if (pageSize < 1) {
            pageSize = 5;
        }
        if (pageSize > 50) {
            pageSize = 50;
        }

        // Validate sort direction
        Sort.Direction direction;
        if (sortDirection.equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
            sortDirection = "ASC";
        } else if (sortDirection.equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
            sortDirection = "DESC";
        } else {
            direction = Sort.Direction.ASC;
            sortDirection = "ASC";
        }

        // Create Sort object
        Sort sort = Sort.by(direction, sortBy);

        // Create PageRequest
        PageRequest request = PageRequest.of(pageNumber - 1, pageSize, sort);

        // Get data
        Page<CategoryResponseDto> categoryPage = categoryService.getAllCategories(request);

        // Add to model
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("totalItems", categoryPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDirection", sortDirection);

        return "index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("category", new CategoryRequestDto());
        return "create-category";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("category")   CategoryRequestDto dto,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "create-category";
        }
        try {
            categoryService.createCategory(dto);
            return "redirect:/";  // Redirect with success flag
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "create-category";
        }
    }

}
