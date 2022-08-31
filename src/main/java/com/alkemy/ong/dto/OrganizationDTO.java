package com.alkemy.ong.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
public class OrganizationDTO {


    @NotBlank(message = "Name cannot be empty or null.")

    private String name;

    private String image;

    private String address;
    
    private Integer phone;

    @NotBlank(message = "Email cannot be empty or null.")
    @Email(message = "Email is not valid.")
    private String email;

    @NotBlank(message = "Welcome text cannot be empty or null.")
    private String welcomeText;

    @NotBlank(message = "Text cannot be empty or null.")
    private String aboutUsText;

    private Timestamp timestamp;
}
