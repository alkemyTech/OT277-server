package com.alkemy.ong.service.impl;

import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.exception.UserAlreadyExist;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserEntity getByEmail(String email) {
        Optional<UserEntity> opt = userRepository.findByEmail(email);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return opt.get();
    }

    public void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExist("User already exist: " + email);
        }
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(
                ()->new ParamNotFound("Param not found: "+ id)));
    }
}
