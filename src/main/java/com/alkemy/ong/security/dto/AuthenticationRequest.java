package com.alkemy.ong.security.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AuthenticationRequest {

    @ApiModelProperty(example = "jane@mail.com", required = true, position = 0)
    @Email(message = "Email not valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @ApiModelProperty(example = "12345678", required = true, position = 1)
    @Size(min = 8, message = "Password not valid")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
