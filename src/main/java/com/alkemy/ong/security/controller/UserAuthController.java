package com.alkemy.ong.security.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserDetailsCustomService userDetailsCustomService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        UserDto response;
        try {
            response = userDetailsCustomService.register(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
