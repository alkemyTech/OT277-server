package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.CommentDtoRequest;
import com.alkemy.ong.dto.CommentDtoResponse;
import com.alkemy.ong.dto.ContactDTO;
import com.alkemy.ong.entity.CommentEntity;
import com.alkemy.ong.entity.ContactEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<CommentDtoResponse> commentEntityList2DTOList(List<CommentEntity> CommentEntitiesList){

        List<CommentDtoResponse> Comments = new ArrayList<>();

        for (CommentEntity entity: CommentEntitiesList){
            Comments.add(toDto(entity));
        }
        return Comments;
    }
}
