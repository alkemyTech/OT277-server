package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.exception.UserAlreadyExist;
import com.alkemy.ong.mapper.impl.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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


    public UserDto patchUser(UserDto userDto, String id) {
        var user = getUserByID(id);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setPhoto(userDto.getPhoto());
        return userMapper.toBasicDto(userRepository.save(user));
    }

    public UserEntity getUserByID(String userId){
        return userRepository.findById(userId).orElseThrow(
                ()->new ParamNotFound("User not found: "+ userId));
    }

    public List<UserDto> findAll() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toBasicDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        userRepository.delete(userRepository.findById(id).orElseThrow(
                ()->new ParamNotFound("Param not found: "+ id)));
    }
}
