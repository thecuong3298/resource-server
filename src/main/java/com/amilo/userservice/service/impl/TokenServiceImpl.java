package com.amilo.userservice.service.impl;

import com.amilo.userservice.dto.Token;
import com.amilo.userservice.service.TokenService;
import com.common.rest.CustomRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class TokenServiceImpl implements TokenService {

    private final CustomRestTemplate customRestTemplate;

    @Override
    public Token getToken(String username, String password) {

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("username", username);
        form.add("password", password);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("client1", "secret");
        return customRestTemplate.post("http://localhost:8080/oauth/token", form, new ParameterizedTypeReference<>() {
        }, new HashMap<>(), headers);
    }
}
