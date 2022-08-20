package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserBasicDto {
    @NotBlank(message = "First name cannot be empty or null.")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty or null.")
    private String lastName;

    @NotBlank(message = "Email cannot be empty or null.")
    @Email(message = "Email is not valid.")
    private String email;

    private String photo;
}
