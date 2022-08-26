package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.NewDto;
import com.alkemy.ong.entity.NewEntity;
import com.alkemy.ong.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewMapper implements Mapper<NewDto, NewEntity> {

    @Lazy
    private final CategoryMapper categoryMapper;
    @Override
    public NewDto toDto(NewEntity newEntity) {
        var newDto = new NewDto();
        newDto.setName(newEntity.getName());
        newDto.setContent(newEntity.getContent());
        newDto.setImage(newEntity.getImage());
        newDto.setTimestamps(newEntity.getTimestamps());
        newDto.setType(newEntity.getType());
        newDto.setCategoryDto(categoryMapper.toDto(newEntity.getCategoryEntity()));
        return newDto;
    }

    @Override
    public NewEntity toEntity(NewDto newDto) {
        NewEntity newEntity = new NewEntity();
        newEntity.setName(newDto.getName());
        newEntity.setContent(newDto.getContent());
        newEntity.setImage(newDto.getImage());
        newEntity.setTimestamps(newDto.getTimestamps());
        newEntity.setType(newDto.getType());
        newEntity.setCategoryEntity(categoryMapper.toEntity(newDto.getCategoryDto()));
        return newEntity;
    }

    @Override
    public NewDto toBasicDto(NewEntity newEntity) {
        return null;
    }


}

//