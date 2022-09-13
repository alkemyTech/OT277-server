package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    //modificar como admin!!
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDTO> saveContact(@Valid @RequestBody ContactDTO DTO){
        return ResponseEntity.ok().body(contactService.save(DTO));
    }
}
