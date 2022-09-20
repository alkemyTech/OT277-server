package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.service.NewService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewController {

    private final NewService newService;

    @PostMapping(produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Save news to update information for organizations",
            produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ACCEPTED - News registered successfully",
                    response = NewDtoResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Arguments cannot be null. ",
                    response = ErrorResponse.class)})
    public ResponseEntity<NewDtoResponse> saveNews(@Valid @RequestBody NewDTO newDto){
        NewDtoResponse response = newService.saveNews(newDto) ;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a new", notes = "Delete new by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "ACCEPTED - News delete successfully",
                    response = NewDtoResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class)
    })
    public ResponseEntity<Void> deleteNew(@ApiParam(value ="new id")@PathVariable String id){
        newService.deleteNew(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    } 
    

    @GetMapping(value = "/{id}/comments",
            produces = {"application/json"})
    @ApiOperation(value = "Get new post and their comments",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - New post",
                    response = NewDtoResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "New not found",
                    response = ErrorResponse.class),})
    public ResponseEntity<NewDtoResponse> getNewById(@ApiParam(value ="new id")@PathVariable String id){
        NewDtoResponse result = newService.getNewById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping(value = "/{id}",
            produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Update new post",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "ACCEPTED - New post updated",
                    response = NewDtoResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "New not found",
                    response = ErrorResponse.class),})
    public ResponseEntity<NewDtoResponse> updateNew(@ApiParam(value ="new id")@PathVariable String id, @Valid @RequestBody NewDTO newDto){
        NewDtoResponse result = newService.update(id,newDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

    @GetMapping(produces = {"application/json"})
    @ApiOperation(value = "Get all post whit pagination",
            produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK",
                    response = PageableResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "New not found",
                    response = ErrorResponse.class),})
    public ResponseEntity<PageableResponse> getAllPosts(
            @ApiParam(value = "number page")@RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @ApiParam(value = "page size")@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @ApiParam(value = "order by")@RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy){
        return ResponseEntity.status(HttpStatus.OK).body(
                newService.getAllPosts(page, pageSize, sortBy)
        );
    }
}
