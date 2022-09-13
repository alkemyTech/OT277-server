package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<TestimonialDTO> save(@Valid @RequestBody TestimonialDTO dto){
        TestimonialDTO response = testimonialService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{testId}")
    public ResponseEntity<TestimonialDTO> update(@Valid @RequestBody TestimonialDTO dto,
                                                 @PathVariable String testId) {
        TestimonialDTO response = testimonialService.update(dto, testId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
