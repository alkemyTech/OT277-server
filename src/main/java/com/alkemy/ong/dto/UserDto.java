package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @ApiModelProperty(example = "Jane")
    @NotBlank(message = "First name cannot be empty or null.")
    private String firstName;

    @ApiModelProperty(example = "Doe", position = 1)
    @NotBlank(message = "Last name cannot be empty or null.")
    private String lastName;

    @ApiModelProperty(example = "jane@mail.com", position = 2)
    @NotBlank(message = "Email cannot be empty or null.")
    @Email(message = "Email is not valid.")
    private String email;

    @ApiModelProperty(example = "12345678", required = true, position = 3)
    @NotBlank(message = "Password cannot be empty or null.")
    @Size(min = 8)
    private String password;

    @ApiModelProperty(example = "https://cohorte-agosto-38d749a7.s3.amazonaws.com/picture.png", position = 4)
    private String photo;

    @ApiModelProperty(example = "Bearer access_token", position = 5)
    private String token;
}
