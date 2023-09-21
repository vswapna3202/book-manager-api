package com.techreturners.bookmanager.exception;

public class BookNotFoundException extends RuntimeException {
    private String message;

    public BookNotFoundException(){}
    public BookNotFoundException(String message){
        super(message);
        this.message = message;
    }


}
