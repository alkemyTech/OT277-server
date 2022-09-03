package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotBlank(message = "First name cannot be empty or null.")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty or null.")
    private String lastName;

    @NotBlank(message = "Email cannot be empty or null.")
    @Email(message = "Email is not valid.")
    private String email;

    @NotBlank(message = "Password cannot be empty or null.")
    @Size(min = 8)
    private String password;

    private String photo;

    private String token;
}
