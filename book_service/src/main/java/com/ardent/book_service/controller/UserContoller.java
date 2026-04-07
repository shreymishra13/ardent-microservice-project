package com.ardent.book_service.controller;


import com.ardent.book_service.entity.Book;
import com.ardent.book_service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/books")
public class UserContoller {

    @Autowired
    BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks(){
        return bookService.getAllBooksForUsers();
    }
}
