package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Solo debe permitir al administrador ejecutar estas acciones!

    @GetMapping("/categories")
    public ResponseEntity<PageableResponse> getAllCategories(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        var categories = categoryService.getCategories(page, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping("/categories")
    ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategoryDTOById(@PathVariable String id){
        CategoryDTO result = categoryService.getCategoryDTOById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto, @PathVariable String id){
        CategoryDTO result = categoryService.update(dto,id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
