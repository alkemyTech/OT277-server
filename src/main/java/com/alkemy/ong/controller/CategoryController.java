package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.service.CategoryService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Api(tags = "Category Endpoints", value = "CategoryEndpoints")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(produces = {"application/json"})
    @ApiOperation(value = "Get all categories whit pagination",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    response = PageableResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "Category not found",
                    response = ErrorResponse.class),})
    public ResponseEntity<PageableResponse> getAllCategories(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        var categories = categoryService.getCategories(page, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a category and return it.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - The category was successfully created",
                    response = CategoryDTO.class),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
                    + "cannot be empty or null.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class)})
    @ApiImplicitParam(name = "Authorization", value = "Access Token",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO category) {
        CategoryDTO savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @ApiOperation(value = "Get a category's details.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - The category was found and it return their details",
                    response = CategoryDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class)})
    @ApiImplicitParam(name = "Authorization", value = "Access Token",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    public ResponseEntity<CategoryDTO> getCategoryDTOById(@PathVariable String id){
        CategoryDTO result = categoryService.getCategoryDTOById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(value = "/{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Update a category passed by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - The category was successfully updated"),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
                    + "cannot be empty or null.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND - Member not found.",
                    response = ErrorResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id",
                    value = "Id of the category we want to update",
                    required = true, allowEmptyValue = false,
                    paramType = "path", dataTypeClass = String.class,
                    example = "1"),
            @ApiImplicitParam(name = "Authorization",
                    value = "Access Token",
                    required = true,
                    allowEmptyValue = false,
                    paramType = "header",
                    dataTypeClass = String.class,
                    example = "Bearer access_token")})
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto, @PathVariable String id){
        CategoryDTO result = categoryService.update(dto,id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a category passed by id.")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "NO_CONTENT - The category was successfully deleted"),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND - Member not found.",
                    response = ErrorResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id",
                    value = "Id of the category we want to delete",
                    required = true, allowEmptyValue = false,
                    paramType = "path", dataTypeClass = String.class,
                    example = "1"),
            @ApiImplicitParam(name = "Authorization",
                    value = "Access Token",
                    required = true,
                    allowEmptyValue = false,
                    paramType = "header",
                    dataTypeClass = String.class,
                    example = "Bearer access_token")})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
