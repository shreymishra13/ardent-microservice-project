package com.ardent.book_service.exception;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(BookAlreadyApprovedException.class)
    public ResponseEntity<String> handleBookAlreadyApprovedException(BookAlreadyApprovedException ex){
        log.info("Some exceptions has occurred. Kindly check the error log" + ex.getMessage() );
        log.error("Some exception has occured ");
        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book Already approved");
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookDoesNotExistException(BookNotFoundException ex){
        log.info("Some error has occured. Kindly check error log "+  ex.getMessage());
        log.error("Some error has occured");
        ex.printStackTrace();
        return ResponseEntity.badRequest().body("Book not found with that id");
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex){
        log.info("Some error has occured. Kindly check error log "+  ex.getMessage());
        log.error("Some error has occured");
        ex.printStackTrace();

        return ResponseEntity.badRequest().body("Some Error has occurred");

    }

}
