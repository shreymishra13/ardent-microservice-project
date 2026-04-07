package com.ardent.book_service.repository;


import com.ardent.book_service.entity.Book;
import com.ardent.book_service.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRespository extends JpaRepository<Book, Long> {

    List<Book> findByStatus(Status status);

    List<Book> findByCreatedBy(String createdBy);

    List<Book> findByCreatedByAndStatus(String tempEmail, Status status);
}
