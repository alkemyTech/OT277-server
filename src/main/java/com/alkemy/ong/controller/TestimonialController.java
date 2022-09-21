package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.service.TestimonialService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
@RequiredArgsConstructor
@Api(tags = "Testimonial Endpoints", value = "TestimonialEndpoints")
public class TestimonialController {

    private final TestimonialService testimonialService;

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Create a testimonial", produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Testimonial created",
                    response = TestimonialDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Bad request",
                    response = ErrorResponse.class)})
    @ApiImplicitParam(name = "Authorization",
            value = "Access Token",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    public ResponseEntity<TestimonialDTO> save(@Valid @RequestBody TestimonialDTO dto) {
        TestimonialDTO response = testimonialService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "{id}", produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Update a testimonial", produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Updated Testimonial",
                    response = TestimonialDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Bad request",
                    response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND - Testimonial not found",
                    response = ErrorResponse.class)})
    @ApiImplicitParam(name = "Authorization",
            value = "Access Token",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    public ResponseEntity<TestimonialDTO> update(@Valid @RequestBody TestimonialDTO dto, @PathVariable String testId) {
        TestimonialDTO response = testimonialService.update(dto, testId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(produces = {"application/json"})
    @ApiOperation(value = "Get pageable of testimonial", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - List of testimonial",
                    response = TestimonialDTO.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class)})
    @ApiImplicitParam(name = "Authorization",
            value = "Access Token",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    public ResponseEntity getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(testimonialService.getAll(page, pageSize, sortBy));
    }

    @DeleteMapping(value = "{id}", produces = {"application/json"})
    @ApiOperation(value = "Delete a testimonial", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Testimonial deleted",
                    response = Void.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND - Testimonial not found",
                    response = ErrorResponse.class)})
    @ApiImplicitParam(name = "Authorization",
            value = "Access Token",
            required = true,
            allowEmptyValue = false,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        testimonialService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
