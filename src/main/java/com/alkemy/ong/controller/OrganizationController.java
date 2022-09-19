package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private  OrganizationService organizationService;

    @GetMapping("/public/{id}")
    public ResponseEntity<OrganizationDTO> publicInformation(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getPublicInformation(id));
    }

    @PutMapping("/public/{id}")
    public ResponseEntity<OrganizationDTO> update(@Valid @RequestBody OrganizationDTO dto, @PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.update(dto, id));
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> save(@Valid @RequestBody OrganizationDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        organizationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
