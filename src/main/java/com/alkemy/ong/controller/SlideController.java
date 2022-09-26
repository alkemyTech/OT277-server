package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideDTO;
import com.alkemy.ong.dto.SlideDTOResponse;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/slides")
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
    public ResponseEntity getAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {
        return ResponseEntity.ok().body(iSlideService.getAll(page, pageSize, sortBy));
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

