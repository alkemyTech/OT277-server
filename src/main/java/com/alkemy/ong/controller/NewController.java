package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewDTO;
import com.alkemy.ong.service.NewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
