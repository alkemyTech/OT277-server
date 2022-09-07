package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Getter
@Setter
public class NewDTO {
    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Image cannot be empty")
    private String image;

    @NotNull(message = "category id cannot be empty")
    private String categoryId;

    private Timestamp timestamps;

    @NotNull(message = "Type cannot be empty")
    private String type;
}
