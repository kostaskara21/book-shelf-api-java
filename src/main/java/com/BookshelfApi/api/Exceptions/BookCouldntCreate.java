package com.BookshelfApi.api.Exceptions;

public class BookCouldntCreate extends RuntimeException{
    public BookCouldntCreate(String message) {
        super(message);
    }
}
