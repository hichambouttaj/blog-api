package com.bojji.blogapi.service;

import com.bojji.blogapi.dto.LoginRequestDto;
import com.bojji.blogapi.dto.RegisterRequestDto;

public interface AuthService {
    String login(LoginRequestDto loginDto);
    String register(RegisterRequestDto registerDto);
}
