package com.amilo.userservice.controller;

import com.amilo.userservice.dto.Token;
import com.amilo.userservice.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("oauth")
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("token")
    public Token getToken(String username, String password) {
        return tokenService.getToken(username, password);
    }
}
