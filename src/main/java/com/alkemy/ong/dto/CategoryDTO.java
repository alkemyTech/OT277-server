package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
public class CategoryDTO {

    private String name;

    private String description;

    private String image;

    private Timestamp timestamps;
}
