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
public class ContactDTO {

        @NotBlank(message = "name cannot be empty or null.")
        private String name;

        private String phone;

        @NotBlank(message = "Email cannot be empty or null.")
        @Email(message = "Email is not valid.")
        private String email;

        @Size(min = 5)
        private String message;
}
