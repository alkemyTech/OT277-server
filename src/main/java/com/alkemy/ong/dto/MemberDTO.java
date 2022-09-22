package com.alkemy.ong.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
public class MemberDTO {

    @ApiModelProperty(example = "Juan", position = 1, required = true)
    @NotEmpty(message="Name cannot be empty")
    private String name;

    @ApiModelProperty(example = "https://www.facebook.com/JuanPepito", position = 2)
    private String facebookUrl;

    @ApiModelProperty(example = "https://www.instagram.com/JuanPepito", position = 3)
    private String instagramUrl;

    @ApiModelProperty(example = "https://www.linkedin.com/JuanPepito", position = 4)
    private String linkedinUrl;

    @ApiModelProperty(example = "https://cohorte-agosto-38d749a7.s3.amazonaws.com/picture.png", position = 5, required = true)
    @NotEmpty(message="Image cannot be empty")
    private String image;

    @ApiModelProperty(example = "This is a member", position = 6)
    private String description;

    @ApiModelProperty(dataType = "Date", example = "2022/09/20", position = 7)
    private Timestamp timestamp;
}
