package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getContacts(){
        List<ContactDTO> result = contactService.getContacts();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
