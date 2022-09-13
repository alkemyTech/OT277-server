package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDtoRequest {

    private String userId;

    private String newsId;

    private String body;
}
