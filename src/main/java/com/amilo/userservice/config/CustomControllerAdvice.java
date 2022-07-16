package com.amilo.userservice.config;

import com.common.dto.ResponseWrapper;
import com.common.rest.response.CommonErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(OAuth2IntrospectionException.class)
    public ResponseEntity<ResponseWrapper> handleAuthorizationException(OAuth2IntrospectionException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseWrapper<>(CommonErrorCode.FORBIDDEN));
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ResponseWrapper> handleAuthorizationException(HttpClientErrorException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseWrapper<>(CommonErrorCode.FORBIDDEN));
    }
}
