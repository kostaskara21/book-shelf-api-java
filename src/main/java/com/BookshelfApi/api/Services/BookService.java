package com.BookshelfApi.api.Services;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Dtos.BooksResponseEntity;
import com.BookshelfApi.api.Models.Books;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookService {

    BooksResponseEntity getAllBooks(int PageNumber, int PageSize);

    Books createBook(BooksDto BookDto);

    BooksDto updateBook(int id,BooksDto BookDto);

    BooksDto getBookById(int id);

    void deleteBook(int id);

}
