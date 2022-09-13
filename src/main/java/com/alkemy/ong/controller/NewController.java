package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.dto.NewDtoResponse;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewController {

    private final NewService newService;

    @PostMapping
    public ResponseEntity<NewDtoResponse> saveNews(@Valid @RequestBody NewDTO newDto){
        NewDtoResponse response = newService.saveNews(newDto) ;
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNew(@PathVariable String id){
        newService.deleteNew(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    } 
    

    @GetMapping("/{id}")
    public ResponseEntity<NewDtoResponse> getNewById(@PathVariable String id){
        NewDtoResponse result = newService.getNewById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewDtoResponse> updateNew(@PathVariable String id, @Valid @RequestBody NewDTO newDto){
        NewDtoResponse result = newService.update(id,newDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }
}
