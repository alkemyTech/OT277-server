package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.service.impl.SlideServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/slides")
@RequiredArgsConstructor
public class SlideController {

    private final SlideServiceImpl slideService;

    @PostMapping
    public ResponseEntity<SlideDTOResponse> saveActivity(@RequestBody SlideDTO dto) {
        return ResponseEntity.ok().body(slideService.saveSlide(dto));
    }

}
