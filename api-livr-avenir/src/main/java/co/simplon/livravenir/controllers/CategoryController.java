package co.simplon.livravenir.controllers;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.livravenir.dtos.LabelValue;
import co.simplon.livravenir.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
	    CategoryService categoryService) {
	this.categoryService = categoryService;
    }

    @GetMapping
    public Collection<LabelValue> getAllCategories() {
	return categoryService.getAllCategories();

    }

}
