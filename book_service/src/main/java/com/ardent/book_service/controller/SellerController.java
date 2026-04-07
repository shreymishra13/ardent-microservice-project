package com.ardent.book_service.controller;


import com.ardent.book_service.dto.BookRequestDTO;
import com.ardent.book_service.entity.Book;
import com.ardent.book_service.entity.Status;
import com.ardent.book_service.service.BookService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/books")
@PreAuthorize("hasRole('SELLER')")
public class SellerController {

    @Autowired
    BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false)Status status , Authentication authentication){
        String user =authentication.getName();
        return bookService.getAllBooksBySellerId(status , user);
    }



    @PostMapping()
    public ResponseEntity<String> addBook(@Valid @RequestBody BookRequestDTO bookRequestDTO , Authentication authentication){

        String user = authentication.getName();
        return bookService.addBook(bookRequestDTO , user);

    }
}
