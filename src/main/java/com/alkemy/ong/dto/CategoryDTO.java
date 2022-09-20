package com.alkemy.ong.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDTO {

    @ApiModelProperty(example = "Tech", position = 1)
    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    @ApiModelProperty(example = "Tech's category", position = 2)
    private String description;

    @ApiModelProperty(example = "https://cohorte-agosto-38d749a7.s3.amazonaws.com/picture.png", position = 3)
    private String image;

    @ApiModelProperty(dataType = "Date", example = "2022/09/20", position = 4)
    private Timestamp timestamps;
}
