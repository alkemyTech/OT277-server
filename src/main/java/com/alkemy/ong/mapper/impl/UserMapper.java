package com.alkemy.ong.mapper.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserDto, UserEntity> {

    @Override
    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoto(dto.getPhoto());
        return entity;
    }

    @Override
    public UserDto toBasicDto(UserEntity entity) {
        UserDto response = new UserDto();
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhoto(entity.getPhoto());
        return response;
    }

    @Override
    public UserDto toDto(UserEntity entity) {
        return null;
    }

}
