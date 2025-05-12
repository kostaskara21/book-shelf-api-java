package com.BookshelfApi.api.Services;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Dtos.BooksResponseEntity;
import com.BookshelfApi.api.Models.Books;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public interface BookService {

    BooksResponseEntity GetAllBooks(int PageNumber, int PageSize);

    Books CreateBook(BooksDto BookDto);

    BooksDto UpdateBook(int id,BooksDto BookDto);

    BooksDto GetBookById(int id);

    void DeleteBook(int id);

}
