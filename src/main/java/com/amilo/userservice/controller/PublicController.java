package com.amilo.userservice.controller;

import com.common.dto.ResponseWrapper;
import com.common.rest.CustomRestTemplate;
import com.common.rest.error.CommonException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("public")
public class PublicController {

    private final CustomRestTemplate customRestTemplate;

    public PublicController(CustomRestTemplate customRestTemplate) {
        this.customRestTemplate = customRestTemplate;
    }


    @GetMapping("test")
    public String test(Authentication authentication) {
        return "restResponse";
    }
}
