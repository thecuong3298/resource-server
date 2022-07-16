package com.amilo.userservice.controller;

import com.common.dto.ResponseWrapper;
import com.common.rest.error.CommonException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {


    @GetMapping("request")
    @PreAuthorize("hasAuthority('ADMINS')")
    public ResponseWrapper<String> test(Authentication authentication) {
        return new ResponseWrapper("hi");
    }
    @GetMapping("request2")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseWrapper<String> test2(Authentication authentication) {
        throw new CommonException("400", "hẹo");
    }
}
