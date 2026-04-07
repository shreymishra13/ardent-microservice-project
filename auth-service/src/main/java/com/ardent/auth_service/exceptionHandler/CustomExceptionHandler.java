package com.ardent.auth_service.exceptionHandler;


import com.ardent.auth_service.dto.ApiResponseDTO;
import com.ardent.auth_service.dto.LoginResponseDTO;
import com.ardent.auth_service.dto.SignupResponseDTO;
import com.ardent.auth_service.exceptions.UserAlreadyExistException;
import com.ardent.auth_service.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<SignupResponseDTO> handleUserAlreadyExistException (UserAlreadyExistException ex){

        log.info("Some Exception has occurred : " + ex.getMessage());
        log.error("Some Exception has occurred : " + ex.getMessage());
        return  ResponseEntity.badRequest().body(
                SignupResponseDTO.builder().email(null).message("User Already Exists!").build()
        );

    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<LoginResponseDTO> handleUserNotFountException(UserNotFoundException ex){
        log.info("Some exception has occurred : "+ ex.getMessage());
        return ResponseEntity.badRequest().body(
                LoginResponseDTO.builder()
                        .message("User Not found")
                        .accessToken(null)
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleExceptions(Exception ex){
        log.info("Some exception has occurred" + ex.getMessage());
        return ResponseEntity.badRequest().body(
                ApiResponseDTO.builder()
                        .success(false)
                        .data(null)
                        .message(ex.getMessage() + " Contact Admin")
                        .build()
        );
    }
}
