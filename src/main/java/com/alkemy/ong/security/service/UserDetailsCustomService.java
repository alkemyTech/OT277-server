package com.alkemy.ong.security.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.mapper.impl.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDto register(UserDto userDto) throws Exception {
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new Exception("User already exists");
        }
        UserEntity user = userMapper.toEntity(userDto);
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return userMapper.toBasicDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), Collections.emptyList());
    }
}
