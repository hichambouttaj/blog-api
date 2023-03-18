package com.bojji.blogapi.controller;

import com.bojji.blogapi.dtos.LoginRequestDto;
import com.bojji.blogapi.dtos.RegisterRequestDto;
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
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // build login
    @PostMapping("/login")
    public ResponseEntity<String> loginHandler(@Valid @RequestBody LoginRequestDto loginDto) {
        String response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // build register
    @PostMapping("/register")
    public ResponseEntity<String> registerHandler(@Valid @RequestBody RegisterRequestDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
