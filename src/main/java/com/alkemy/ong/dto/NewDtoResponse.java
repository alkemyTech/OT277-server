package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewDtoResponse {

    @ApiModelProperty(example = "Juan", position = 2, required = true)
    private String name;

    @ApiModelProperty(example = "Content of the post", position = 3, required = true)
    private String content;

    @ApiModelProperty(example = "https://cohorte-agosto-38d749a7.s3.amazonaws.com/picture.png", position = 5)
    private String image;

    @ApiModelProperty(required = true)
    private CategoryDTO category;

    @ApiModelProperty(dataType = "Date", example = "2022/09/20", position = 4)
    private Timestamp timestamps;

    @ApiModelProperty(example = "News", position = 1)
    private String type;

    @ApiModelProperty(example = "comments", position = 6)
    Set<CommentDtoResponse> comments = new HashSet<>();

}
