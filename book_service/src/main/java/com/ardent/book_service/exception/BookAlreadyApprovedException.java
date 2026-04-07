package com.ardent.book_service.exception;

public class BookAlreadyApprovedException extends RuntimeException {
    public BookAlreadyApprovedException(String s) {
        super(s);
    }
}
