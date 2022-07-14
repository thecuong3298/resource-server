package com.amilo.userservice.controller;

import com.common.dto.ResponseWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {


    @GetMapping("request")
    public ResponseWrapper<String> test(Authentication authentication) {
        return new ResponseWrapper("hi");
    }
}
