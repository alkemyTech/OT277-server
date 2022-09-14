package com.alkemy.ong.security.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.entity.UserEntity;
import com.alkemy.ong.mapper.impl.UserMapper;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.EmailService;
import com.alkemy.ong.service.impl.RoleServiceImpl;
import com.alkemy.ong.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsCustomService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleServiceImpl roleService;

    private final JwtUtils jwtUtils;

    private final UserServiceImpl userService;

    private final EmailService emailService;


    public UserDto register(UserDto userDto) {
        userService.validateEmail(userDto.getEmail());
        UserEntity user = userMapper.toEntity(userDto);
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setRole(List.of(roleService.getUserRole()));

        //emailService.sendEmailTo(userDto);
        UserDto response = userMapper.toBasicDto(userRepository.save(user));
        response.setToken(jwtUtils.generateToken(user));
        return response;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("username %s not found"));
    }

    public UserDto getMe(HttpServletRequest http) {
        var email = extractUsername(http);
        return userMapper.toBasicDto(userService.getByEmail(email));
    }

    public String extractUsername(HttpServletRequest http){
        String authorizationHeader = http.getHeader("Authorization");
        String email = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            email = jwtUtils.extractUsername(authorizationHeader.substring(7));
        }
        return email;
    }


    public List<String> extractRoles(HttpServletRequest http) {
        String authorizationHeader = http.getHeader("Authorization");
        List<String> roles = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            roles = jwtUtils.extractRoles(authorizationHeader.substring(7));
        }
        return roles;
    }
}
