package com.alkemy.ong.security.controller;

import com.alkemy.ong.dto.UserBasicDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;


    @PostMapping("/register")
    public ResponseEntity<UserBasicDto> register(@Valid @RequestBody UserDto userDto) {
        UserBasicDto response;
        try {
            response = userDetailsCustomService.register(userDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
