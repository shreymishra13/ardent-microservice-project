package com.ardent.auth_service.controller;


import com.ardent.auth_service.dto.*;
import com.ardent.auth_service.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> createUser(@Valid @RequestBody SignupRequestDTO signupRequestDTO){

        return authService.createUser(signupRequestDTO);


    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> logInUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return  authService.login(loginRequestDTO);
    }
}
