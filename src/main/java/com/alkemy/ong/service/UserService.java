package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;

public interface UserService {

    UserEntity getByEmail(String email);

    void validateEmail(String email);

    UserDto patchUser(UserDto userDto, String id);
}
