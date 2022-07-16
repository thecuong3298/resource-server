package com.amilo.userservice.service;

import com.amilo.userservice.dto.Token;

public interface TokenService {

    public Token getToken(String username, String password);
}
