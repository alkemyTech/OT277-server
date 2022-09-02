package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/News")
@RequiredArgsConstructor
public class NewController {

    private final NewService newService;

    @PostMapping
    public ResponseEntity<NewDTO> saveNews(@Valid @RequestBody NewDTO newDto){
        NewDTO response = newService.saveNews(newDto) ;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNew(@PathVariable String id){
        newService.deleteNew(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    } 
    
    @GetMapping("/news/{id}")
    public ResponseEntity<NewDTO> getNewById(@PathVariable String id){
        NewDTO result = newService.getNewById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
