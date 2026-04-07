package com.ardent.book_service.controller;


import com.ardent.book_service.entity.Book;
import com.ardent.book_service.entity.Status;
import com.ardent.book_service.exception.BookAlreadyApprovedException;
import com.ardent.book_service.service.BookService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/books")
public class AdminController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false)Status status){

        return bookService.getAllBooks(status);
    }

    @PutMapping("/{bookId}/approve")
    public ResponseEntity<String> approveBook(@PathVariable Long bookId , Authentication authentication) throws RuntimeException, BookAlreadyApprovedException {

        String user = authentication.getName();
        return bookService.approveBook(bookId , user);


    }


}
