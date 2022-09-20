package com.alkemy.ong.security.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.security.dto.AuthenticationRequest;
import com.alkemy.ong.security.dto.AuthenticationResponse;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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


    @PostMapping(value = "/register", produces = {"application/json"},
            consumes = {"application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Register an user and get the Bearer token",
            produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK - User register successfully",
                    response = UserDto.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Invalid mail o password",
                    response = ErrorResponse.class),
            @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Arguments cannot be null. ",
                    response = ErrorResponse.class)})
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userDetailsCustomService.register(userDto));
    }

    @PostMapping(value = "/login",
            produces = {"application/json"},
            consumes = {"application/json"})
    @ApiOperation(value = "Login an user and get the Bearer token",
            produces = "application/json",
            consumes = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - User authenticated",
                    response = AuthenticationResponse.class),
            @ApiResponse(code = 400, message = "Email not valid " +
                    "\n Password not valid",
                    response = ErrorResponse.class),})
    public ResponseEntity<Object> singIn(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
                            authenticationRequest.getPassword(), null)
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok("false");
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(userDetails.getUsername(), jwt));
    }

    @GetMapping(value = "/me", produces = {"application/json"})
    @ApiImplicitParam(name = "Authorization",
            value = "Access Token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class,
            example = "Bearer access_token")
    @ApiOperation(value = "Get my user details", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - User Details",
                    response = UserDto.class),
            @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
                    response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT_FOUND - User not found",
                    response = ErrorResponse.class)})
    public ResponseEntity<UserDto> getMe(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(userDetailsCustomService.getMe(request));
    }

}
