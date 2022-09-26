package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class TestimonialDTO {

    @ApiModelProperty(value = "name", example = "Name", position = 1)
    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    @ApiModelProperty(value = "image", example = "https://cohorte-agosto-38d749a7.s3.amazonaws.com/picture.png", position = 2)
    private String image;

    @ApiModelProperty(value = "content", example = "It´s a testimonial", position = 3)
    @NotBlank(message = "Content cannot be empty or null.")
    private String content;

}
