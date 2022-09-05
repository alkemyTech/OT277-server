package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getByEmail(String email);

    void validateEmail(String email);

    List<UserDto> findAll();
}
