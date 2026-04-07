package com.ardent.book_service.service;


import com.ardent.book_service.dto.BookRequestDTO;
import com.ardent.book_service.entity.Book;
import com.ardent.book_service.entity.Status;
import com.ardent.book_service.exception.BookAlreadyApprovedException;
import com.ardent.book_service.exception.BookNotFoundException;
import com.ardent.book_service.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRespository bookRespository;

    public ResponseEntity<List<Book>> getAllBooks(Status status){

        if(status == null)
            return ResponseEntity.ok().body(bookRespository.findAll()) ;
        else
            return  ResponseEntity.ok().body(bookRespository.findByStatus(status));

    }

    public ResponseEntity<String> addBook(BookRequestDTO bookRequestDTO , String user) {

        Book book = Book.builder()
                        .genre(bookRequestDTO.getGenre())
                .name(bookRequestDTO.getName())
                .price(bookRequestDTO.getPrice()).discount(bookRequestDTO.getDiscount()).stock(bookRequestDTO.getStock())
                .authorName(bookRequestDTO.getAuthorName()).status(Status.SUBMITTED).description(bookRequestDTO.getDescription())
                .createdBy(user).updatedBy(user).build();

        bookRespository.save(
                book
        );

        return ResponseEntity.ok().body("Book Added Successfully");
    }

    public ResponseEntity<String> approveBook(Long bookId, String user) {

        //first check if the book exists
        Optional<Book> book = bookRespository.findById(bookId);
        System.out.println(book.get().toString());
        if(!book.isPresent()){
            throw new BookNotFoundException("Book doesn't not exist with the mentioned Id : "+ bookId);
        }else if(book.get().getStatus() == Status.APPROVED){
            System.out.println(book.get().getStatus() == Status.APPROVED);
            throw new BookAlreadyApprovedException("Book is already approved with book id : "+ bookId);
        }
        book.get().setApprovedAt(LocalDateTime.now());

        //later get by JWT
        book.get().setApprovedBy(user);
        book.get().setStatus(Status.APPROVED);
        bookRespository.save(book.get());

        return ResponseEntity.ok().body("Book approved successfully");
    }

    public ResponseEntity<List<Book>> getAllBooksBySellerId(Status status , String user) {

        if(status == null || status.equals("")){
            return ResponseEntity.ok().body(bookRespository.findByCreatedBy(user));
        }
        else{
            return ResponseEntity.ok().body(
                    bookRespository.findByCreatedByAndStatus(user , status)
            );
        }

    }

    public ResponseEntity<List<Book>> getAllBooksForUsers() {
        return ResponseEntity.ok().body(bookRespository.findByStatus(Status.APPROVED));
    }
}
