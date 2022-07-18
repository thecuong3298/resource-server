package com.amilo.userservice.controller;

import com.common.resourceserver.dto.Token;
import com.common.rest.CustomRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames.ACCESS_TOKEN;

@RestController
@RequestMapping("public")
public class PublicController {

    private final CustomRestTemplate customRestTemplate;

    public PublicController(CustomRestTemplate customRestTemplate) {
        this.customRestTemplate = customRestTemplate;
    }


    @GetMapping("test")
    public Token getToken(HttpServletResponse response) {
        String cookie = "%s=%s; Path=/; HttpOnly; SameSite=None; Secure=false";
        response.addHeader(HttpHeaders.SET_COOKIE,
                String.format(cookie, ACCESS_TOKEN, "BBXWsMgFDOGWOJaIX7wFdbN-jOU"));
        Cookie cookie1 = new Cookie("refresh", "1234");
        cookie1.setMaxAge(60);
        cookie1.setHttpOnly(true);
        cookie1.setSecure(false);
        cookie1.setPath("/");
        response.addCookie(cookie1);
        return new Token();
    }

    @GetMapping("check")
    public void check(HttpServletRequest request) {
        System.out.println(request.getCookies());
    }
}
