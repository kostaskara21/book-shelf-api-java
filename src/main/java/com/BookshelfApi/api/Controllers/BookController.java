package com.BookshelfApi.api.Controllers;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Dtos.BooksResponseEntity;
import com.BookshelfApi.api.Models.Books;
import com.BookshelfApi.api.Services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.BookshelfApi.api.Mappers.BooksMapper.BooksToBooksDto;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("Books")
    public ResponseEntity<BooksResponseEntity> getBooks(@RequestParam(value = "PageNumber",defaultValue = "0",required = false) int PageNumber, @RequestParam(value = "PageSize",defaultValue = "10",required = false) int PageSize) {
        BooksResponseEntity booksResponseEntity = bookService.GetAllBooks(PageNumber, PageSize);
        return new ResponseEntity<>(booksResponseEntity, HttpStatus.OK);
    }

    @GetMapping("Books/{id}")
    public ResponseEntity<BooksDto> getBooksById(@PathVariable int id) {
        BooksDto Bookdto = bookService.GetBookById(id);
        return new ResponseEntity<>(Bookdto, HttpStatus.OK);
    }

    @PostMapping("Books/Create")
    public ResponseEntity<BooksDto> createBook(@RequestBody BooksDto book) {
        Books createdBook=bookService.CreateBook(book);
        return new ResponseEntity<>(BooksToBooksDto(createdBook),HttpStatus.CREATED);
    }

    @PutMapping("Books/{id}/Update")
    public ResponseEntity<BooksDto> UpdateBook(@PathVariable  int id, @RequestBody BooksDto book) {
        BooksDto updatedBook=bookService.UpdateBook(id, book);
        return new ResponseEntity<>(updatedBook,HttpStatus.OK);
    }

    @DeleteMapping("Books/{id}/Delete")
    public ResponseEntity<String> DeleteBook(@PathVariable int id) {
        bookService.DeleteBook(id);
        return new ResponseEntity<>("Book deleted Succesfully",HttpStatus.OK);
    }


}
