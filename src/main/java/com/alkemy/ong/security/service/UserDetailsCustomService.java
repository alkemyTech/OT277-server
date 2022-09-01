package com.alkemy.ong.security.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.mapper.impl.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtUtils jwtUtils;


    public UserDto register(UserDto userDto) throws Exception {
        UserEntity user = userMapper.toEntity(userDto);
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setRole(List.of(roleService.getUserRole()));
        return userMapper.toBasicDto(userRepository.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("username %s not found"));
    }

    public UserDto getMe(HttpServletRequest http) {
        String authorizationHeader = http.getHeader("Authorization");
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            email = jwtUtils.extractUsername(authorizationHeader.substring(7));
        }
        return userMapper.toBasicDto(getByEmail(email));
    }


    private UserEntity getByEmail(String email) {
        Optional<UserEntity> opt = userRepository.findByEmail(email);
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return opt.get();
    }
}
