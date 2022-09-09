package com.alkemy.ong.controller;

import com.alkemy.ong.security.dto.SlideDTOResponse;
import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService iSlideService;

    @GetMapping
    public ResponseEntity<List<SlideDTOResponse>> getAll() {
        return ResponseEntity.ok().body(iSlideService.getSlides());
    }
}
