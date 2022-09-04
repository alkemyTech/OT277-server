package com.alkemy.ong.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityDTO {

    @NotBlank(message = "Name cannot be empty or null.")
    private String name;

    @NotBlank(message = "Content cannot be empty or null.")
    private String content;

    private String image;
}
