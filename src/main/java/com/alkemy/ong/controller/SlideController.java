package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.alkemy.ong.dto.SlideDTO;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService iSlideService;

    @GetMapping("{id}")
    public ResponseEntity<SlideDTOResponse> getById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(iSlideService.getById(id));
    }
    @GetMapping
    public ResponseEntity<List<SlideDTOResponse>> getAll() {
        return ResponseEntity.ok().body(iSlideService.getSlides());
    }

    @PostMapping
    public ResponseEntity<SlideDTOResponse> saveActivity(@RequestBody SlideDTO dto) {
        return ResponseEntity.ok().body(iSlideService.saveSlide(dto));
    }

}
