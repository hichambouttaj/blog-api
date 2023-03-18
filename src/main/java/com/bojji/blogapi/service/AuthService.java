package com.bojji.blogapi.service;

import com.bojji.blogapi.dtos.LoginRequestDto;
import com.bojji.blogapi.dtos.RegisterRequestDto;

public interface AuthService {
    String login(LoginRequestDto loginDto);
    String register(RegisterRequestDto registerDto);
}
