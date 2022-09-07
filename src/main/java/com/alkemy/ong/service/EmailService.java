package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;

public interface EmailService {

    void sendEmailTo(UserDto userDto);
}
