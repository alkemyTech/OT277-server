package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDtoRequest {

    private String user;

    private String news;

    private String body;
}
