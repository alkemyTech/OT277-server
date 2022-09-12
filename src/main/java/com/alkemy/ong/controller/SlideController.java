package com.alkemy.ong.controller;

import com.alkemy.ong.service.SlideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideService iSlideService;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSlide(@PathVariable String id) {
        iSlideService.deleteSlide(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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
