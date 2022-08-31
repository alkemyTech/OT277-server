package com.alkemy.ong.security.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserAuthController {

    private final UserDetailsCustomService userDetailsCustomService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenUtil;
    private  final EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        UserDto response;
        try {
            response = userDetailsCustomService.register(userDto);
            emailService.sendEmailTo(userDto.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> singIn(@Valid @RequestBody AuthenticationRequest authenticationRequest)
            throws Exception{
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword(), null)
            );
            userDetails = (UserDetails)auth.getPrincipal();
        }catch (BadCredentialsException e){
            return ResponseEntity.ok(false);
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(userDetails.getUsername(), jwt));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsCustomService.getMe(request));
    }

}
