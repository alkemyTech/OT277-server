package com.alkemy.ong.security.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequest {
    @Email
    private String email;
    @Size(min = 8)
    private String password;
}
