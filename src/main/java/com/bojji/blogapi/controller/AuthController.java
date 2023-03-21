package com.bojji.blogapi.controller;

import com.bojji.blogapi.dto.JwtAuthResponse;
import com.bojji.blogapi.dto.LoginRequestDto;
import com.bojji.blogapi.dto.RegisterRequestDto;
import com.bojji.blogapi.dto.TokenType;
import com.bojji.blogapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    // build login
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginHandler(@Valid @RequestBody LoginRequestDto loginDto) {
        String token = authService.login(loginDto);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setType(TokenType.Bearer);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // build register
    @PostMapping("/register")
    public ResponseEntity<JwtAuthResponse> registerHandler(@Valid @RequestBody RegisterRequestDto registerDto) {
        String token = authService.register(registerDto);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        response.setType(TokenType.Bearer);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
