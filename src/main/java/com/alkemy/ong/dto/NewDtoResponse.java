package com.alkemy.ong.dto;

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
public class NewDtoResponse {

    private String name;

    private String content;

    private String image;

    private CategoryDTO category;

    private Timestamp timestamps;

    private String type;

    Set<CommentDtoResponse> comments = new HashSet<>();

}
