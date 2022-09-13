package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationDTO> publicInformation(@PathVariable String id){
        OrganizationDTO result = organizationService.getPublicInformation(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<OrganizationDTO> update(@Valid @RequestBody OrganizationDTO dto, @PathVariable String id){
        OrganizationDTO result = organizationService.update(dto,id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> save(@Valid @RequestBody OrganizationDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.save(dto));
    }

}
