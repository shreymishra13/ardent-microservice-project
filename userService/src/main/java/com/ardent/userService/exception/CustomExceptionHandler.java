package com.ardent.userService.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {



    @ExceptionHandler(InvalidUserDetailException.class)
    public ResponseEntity<String> handleInvalidUserDetailException(InvalidUserDetailException ex){

        log.info("Some exception has occurred:  "+ ex.getMessage());
        log.error("Some exception has occurred:  "+ ex.getMessage());

        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex){

        log.info("Some exception has occurred:  "+ ex.getMessage());
        log.error("Some exception has occurred:  "+ ex.getMessage());

        return ResponseEntity.badRequest().body("Some error has occured");

    }
}
