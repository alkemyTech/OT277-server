package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDTO {

    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    private String description;

    private String image;

    private Timestamp timestamps;
}
