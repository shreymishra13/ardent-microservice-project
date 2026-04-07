package com.ardent.auth_service.controller;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping()
    public ResponseEntity<String> getUserEmail(Authentication auth){

        if(null == auth){
            throw  new RuntimeException("Auth is nulll");
        }

        return  ResponseEntity.ok().body(
                auth.getName()
        );
    }
}
