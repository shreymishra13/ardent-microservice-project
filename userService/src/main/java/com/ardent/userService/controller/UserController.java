package com.ardent.userService.controller;


import com.ardent.userService.dto.UserDTO;
import com.ardent.userService.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping("/details")
    public ResponseEntity<String> addUserDetail(@Valid @RequestBody UserDTO userDTO){
        return userService.addUserDetail(userDTO);
    }
}
