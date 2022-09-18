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

    @PostMapping
    public ResponseEntity<SlideDTOResponse> saveActivity(@RequestBody SlideDTO dto) {
        return ResponseEntity.ok().body(iSlideService.saveSlide(dto));
    }

    @PutMapping("{id}")
    public ResponseEntity<SlideDTOResponse> update(@RequestBody SlideDTO dto, @PathVariable String id){
        return ResponseEntity.ok().body(iSlideService.update(id, dto));
    }
}

