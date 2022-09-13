package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.entity.CommentEntity;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper{

    public CommentEntity toEntity(CommentDtoRequest dto) {
        var entity = new CommentEntity();
        entity.setBody(dto.getBody());
        return entity;
    }

    public CommentDtoResponse toDto(CommentEntity commentEntity) {
        var dto = new CommentDtoResponse();
        dto.setBody(commentEntity.getBody());
        return dto;
    }
}
