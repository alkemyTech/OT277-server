package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<Set<CategoryDTO>> getCategories(){
        Set<CategoryDTO> result = categoryService.getCategories();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/categories")
    ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }
}
