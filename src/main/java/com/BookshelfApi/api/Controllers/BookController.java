package com.BookshelfApi.api.Controllers;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Dtos.BooksResponseEntity;
import com.BookshelfApi.api.Models.Books;
import com.BookshelfApi.api.Services.BookService;
import com.BookshelfApi.api.constants.BookEndpointsConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.BookshelfApi.api.Mappers.BooksMapper.booksToBooksDto;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private  final BookService bookService;


        @GetMapping(BookEndpointsConstants.GET_ALL_BOOKS)
        public ResponseEntity<BooksResponseEntity> getBooks(@RequestParam(value = "PageNumber",defaultValue = "0",required = false) int PageNumber, @RequestParam(value = "PageSize",defaultValue = "10",required = false) int PageSize) {
            BooksResponseEntity booksResponseEntity = bookService.getAllBooks(PageNumber, PageSize);
            return new ResponseEntity<>(booksResponseEntity, HttpStatus.OK);
        }

    @GetMapping(BookEndpointsConstants.GET_BOOK)
    public ResponseEntity<BooksDto> getBooksById(@PathVariable int id) {
        BooksDto Bookdto = bookService.getBookById(id);
        return new ResponseEntity<>(Bookdto, HttpStatus.OK);
    }

    @PostMapping(BookEndpointsConstants.CREATE_BOOK)
    public ResponseEntity<BooksDto> createBook(@RequestBody BooksDto book) {
        Books createdBook=bookService.createBook(book);
        return new ResponseEntity<>(booksToBooksDto(createdBook),HttpStatus.CREATED);
    }

    @PutMapping(BookEndpointsConstants.UPDATE_BOOK)
    public ResponseEntity<BooksDto> UpdateBook(@PathVariable  int id, @RequestBody BooksDto book) {
        BooksDto updatedBook=bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook,HttpStatus.OK);
    }

    @DeleteMapping(BookEndpointsConstants.DELETE_BOOK)
    public ResponseEntity<String> DeleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book deleted Succesfully",HttpStatus.OK);
    }


}
