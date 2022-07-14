package com.amilo.userservice.controller;

import com.common.dto.ResponseWrapper;
import com.common.rest.CustomRestTemplate;
import com.common.rest.error.CommonException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
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
    public ResponseWrapper<String> test() {
        ResponseWrapper<String> restResponse = customRestTemplate.get("http://192.168.1.121:8090/api/request",
                new ParameterizedTypeReference<>() {
                }, new HashMap<>());
        return restResponse;
    }
}
