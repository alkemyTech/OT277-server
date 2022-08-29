package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id){
        CategoryDTO result = categoryService.getCategoryDTOById(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
