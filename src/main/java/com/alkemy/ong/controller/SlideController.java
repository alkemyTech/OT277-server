package com.alkemy.ong.controller;

import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService iSlideService;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSlide(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
