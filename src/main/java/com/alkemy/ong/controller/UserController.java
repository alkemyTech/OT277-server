package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> patchUser(@PathVariable String id, @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                this.userService.patchUser(userDto, id));
     }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userService.findAll());
    }
 }