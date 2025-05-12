package com.BookshelfApi.api.Services.Impl;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Dtos.BooksResponseEntity;
import com.BookshelfApi.api.Exceptions.BookCouldntCreate;
import com.BookshelfApi.api.Exceptions.BookNotFoundException;
import com.BookshelfApi.api.Models.Books;
import com.BookshelfApi.api.Repository.BookRepo;
import com.BookshelfApi.api.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.BookshelfApi.api.Mappers.BooksMapper.*;

@Service
public class BookImpl implements BookService {

    private BookRepo bookRepo;

    @Autowired
    public BookImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public BooksResponseEntity GetAllBooks(int PageNumber, int PageSize) {
        Pageable pageable=  PageRequest.of(PageNumber,PageSize);
        Page<Books> Books = bookRepo.findAll(pageable);
        List<Books> ListofBooks = Books.getContent();



        List<BooksDto> content=ListofBooks.stream().map(book->BooksToBooksDto(book)).collect(Collectors.toList());
        BooksResponseEntity booksResponseEntity = new BooksResponseEntity();
        booksResponseEntity.setContext(content);
        booksResponseEntity.setPageNumber(Books.getNumber());
        booksResponseEntity.setPageSize(Books.getSize());
        booksResponseEntity.setTotalElements(Books.getNumberOfElements());
        booksResponseEntity.setTotalPages(Books.getTotalPages());
        booksResponseEntity.setLast(Books.isLast());
        return booksResponseEntity;
    }

    @Override
    public Books CreateBook(BooksDto BookDto) {
        if(BookDto.getTitle().isEmpty() || BookDto.getAuthor().isEmpty()||BookDto.getDescription().isEmpty() ){
            throw new BookCouldntCreate("Missing Required Fields");
        }
       Books books = BooksDtoToBooks(BookDto);
       return bookRepo.save(books);
    }

    @Override
    public BooksDto UpdateBook(int id, BooksDto BookDto) {
        Books book= bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("Book not found"));
        book.setAuthor(BookDto.getAuthor());
        book.setTitle(BookDto.getTitle());
        book.setDescription(BookDto.getDescription());
        return BooksToBooksDto(bookRepo.save(book));
    }

    @Override
    public BooksDto GetBookById(int id) {
        Books books = bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("There is Not Such A Book with this Id"));
        return BooksToBooksDto(books);
    }

    @Override
    public void DeleteBook(int id) {
        Books book=bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("Book not found"));
        bookRepo.delete(book);
    }
}
