package com.example.to_do_list.controller;

import com.example.to_do_list.dto.LoginRequestDto;
import com.example.to_do_list.dto.RegisterRequestDto;
import com.example.to_do_list.dto.TokenResponseDto;
import com.example.to_do_list.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto dto){
        return service.login(dto);
    }

    @PostMapping("/register")
    public TokenResponseDto register(@Valid @RequestBody RegisterRequestDto dto){
        return service.register(dto);
    }
}
