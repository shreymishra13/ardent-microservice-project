package com.ardent.auth_service.service;

import com.ardent.auth_service.dto.*;
import com.ardent.auth_service.entity.User;
import com.ardent.auth_service.exceptions.InvalidPasswordException;
import com.ardent.auth_service.exceptions.UserAlreadyExistException;
import com.ardent.auth_service.exceptions.UserNotFoundException;
import com.ardent.auth_service.repository.UserRepository;
import com.ardent.auth_service.security.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public ResponseEntity<SignupResponseDTO> createUser(@Valid SignupRequestDTO signupRequestDTO) {

        if(userRepository.findByEmail(signupRequestDTO.getEmail()).isPresent()){
            throw new UserAlreadyExistException("User Already Exist");
        }
        User user = User.builder()
                .email(signupRequestDTO.getEmail())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                        .userRole(signupRequestDTO.getUserRole())
                        .build();

      userRepository.save(user);

       return ResponseEntity.ok(
               SignupResponseDTO.builder()
                       .message("User Created Successfully")
                       .email(signupRequestDTO.getEmail())
                       .build()
       );



    }

    public ResponseEntity<LoginResponseDTO> login(@Valid LoginRequestDTO loginRequestDTO){
        Optional<User> user = userRepository.findByEmail(loginRequestDTO.getEmail());
        //check if the user is present
        if(!user.isPresent()){
            throw  new UserNotFoundException("Email not found!");

        }
//        check if the password is correct
        if(!passwordEncoder.matches(loginRequestDTO.getPassword() , user.get().getPassword())){
            throw new InvalidPasswordException("Wrong Password Entered");
        }

        //now generate JWT
        String token = jwtUtil.generateToken(
            user.get().getEmail(),
                user.get().getUserRole().toString()
        );



        return ResponseEntity.ok(LoginResponseDTO.builder().accessToken(token).message("Logged In successfully").build());

    }

}
