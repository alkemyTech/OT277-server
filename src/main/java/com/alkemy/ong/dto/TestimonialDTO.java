package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TestimonialDTO {

    @NotBlank(message = "The name cannot be empty")
    private String name;


    private String image;

    @NotBlank(message = "The content cannot be empty")
    private String content;

}
