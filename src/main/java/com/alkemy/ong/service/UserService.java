package com.alkemy.ong.service;

import com.alkemy.ong.entity.UserEntity;

public interface UserService {

    UserEntity getByEmail(String email);

    void validateEmail(String email);

    void deleteUser(String id);
}
