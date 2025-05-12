package com.BookshelfApi.api.Mappers;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Models.Books;

public class BooksMapper {

    public static Books BooksDtoToBooks(BooksDto booksDto) {
        return new Books().builder()
                .Title(booksDto.getTitle())
                .Description(booksDto.getDescription())
                .Author(booksDto.getAuthor())
                .build();
    }

    public static BooksDto BooksToBooksDto(Books books) {
        return new BooksDto().builder()
                .Title(books.getTitle())
                .Description(books.getDescription())
                .Author(books.getAuthor())
                .build();
    }
}
