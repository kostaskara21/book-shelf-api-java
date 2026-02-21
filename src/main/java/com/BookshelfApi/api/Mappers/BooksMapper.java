package com.BookshelfApi.api.Mappers;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Models.Books;

public class BooksMapper {

    public static Books booksDtoToBooks(BooksDto booksDto) {
        return Books.builder()
                .Title(booksDto.getTitle())
                .Description(booksDto.getDescription())
                .Author(booksDto.getAuthor())
                .build();
    }

    public static BooksDto booksToBooksDto(Books books) {
        return BooksDto.builder()
                .Title(books.getTitle())
                .Description(books.getDescription())
                .Author(books.getAuthor())
                .build();
    }
}
