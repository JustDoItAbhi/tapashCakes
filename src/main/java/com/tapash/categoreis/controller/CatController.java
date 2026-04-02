package com.tapash.categoreis.controller;

import com.tapash.categoreis.category_dtos.CategoryRequestDto;
import com.tapash.categoreis.category_service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CatController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
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
