package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.service.SlideService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slides")
@Api(tags = "Slide Endpoints", value = "SlideEndpoints")
//@RequiredArgsConstructor
public class SlideController {

    @Autowired
    private  SlideService iSlideService;


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSlide(@PathVariable String id) {
        iSlideService.deleteSlide(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<SlideDTOResponse> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(iSlideService.getByIdResponse(id));

    }

    @GetMapping
    public ResponseEntity<List<SlideDTOResponse>> getAll() {
        return ResponseEntity.ok().body(iSlideService.getSlides());
    }

    @PostMapping(consumes = {"application/json"},
            produces = {"application/json"})
    @ApiOperation(value = "Create a slide")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "CREATED - The slide was successfully created"),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Certain arguments "
                    + "cannot be empty or null.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class)})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "x-file-name", value = "File name",
                    required = true,
                    allowEmptyValue = false,
                    paramType = "header",
                    dataTypeClass = String.class,
                    example = "name"),
            @ApiImplicitParam(name = "x-content-typee", value = "Content type",
                    required = true,
                    allowEmptyValue = false,
                    paramType = "header",
                    dataTypeClass = String.class,
                    example = "image/png"),
            @ApiImplicitParam(name = "Authorization", value = "Access Token",
                    required = true,
                    allowEmptyValue = false,
                    paramType = "header",
                    dataTypeClass = String.class,
                    example = "Bearer access_token")
    })
    public ResponseEntity<SlideDTOResponse> saveActivity(@RequestBody SlideDTO dto) {
        return ResponseEntity.ok().body(iSlideService.saveSlide(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<SlideDTOResponse> update(@RequestBody SlideDTO dto, @PathVariable String id){
        return ResponseEntity.ok().body(iSlideService.update(id, dto));
    }
}

