package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UserBasicDto;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity dtoToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        entity.setPhoto(dto.getPhoto());
        return entity;
    }

    public UserBasicDto entityToDto(UserEntity entity){
        UserBasicDto response = new UserBasicDto();
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setEmail(entity.getEmail());
        response.setPhoto(entity.getPhoto());
        return response;
    }
}
