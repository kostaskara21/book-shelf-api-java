package com.BookshelfApi.api.Services.Impl;

import com.BookshelfApi.api.Dtos.BooksDto;
import com.BookshelfApi.api.Dtos.BooksResponseEntity;
import com.BookshelfApi.api.Exceptions.BookCouldntCreate;
import com.BookshelfApi.api.Exceptions.BookNotFoundException;
import com.BookshelfApi.api.Models.Books;
import com.BookshelfApi.api.Repository.BookRepo;
import com.BookshelfApi.api.Services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
public class BookImpl implements BookService {

    private final  BookRepo bookRepo;


    @Override
    public BooksResponseEntity getAllBooks(int PageNumber, int PageSize) {

        log.info("Received Request fetching books in {} pages", PageNumber);

        Pageable pageable=  PageRequest.of(PageNumber,PageSize);
        Page<Books> books = bookRepo.findAll(pageable);
        List<Books> listofBooks = books.getContent();

        log.info("{} Books found in database", listofBooks.size());
        List<BooksDto> content=mapToBooksDto(listofBooks);

        return makeBooksResponseEntity(books,content);
    }




    @Override
    public Books createBook(BooksDto bookDto) {
        log.info("Received Request creating book {}", bookDto.getTitle());
        validateBook(bookDto);
        Books books = booksDtoToBooks(bookDto);
        log.info("{} Book created", books.getTitle());
        return bookRepo.save(books);
    }



    @Override
    public BooksDto updateBook(int id, BooksDto bookDto) {
        log.info("Received Request updatig book {}", bookDto.getTitle());
        Books book= findBookById(id);
        log.info("{} Book found", book.getTitle());
        makeBookfromBookDto(bookDto,book);
        log.info("{} Book updated", book.getTitle());
        return booksToBooksDto(bookRepo.save(book));
    }


    @Override
    public BooksDto getBookById(int id) {
        log.info("Received Request to find book with id  {}", id);
        Books books = findBookById(id);
        log.info("{} Book found", books.getTitle());
        return booksToBooksDto(books);
    }

    @Override
    public void deleteBook(int id) {
        log.info("Received Request to delete book with id  {}", id);
        Books book=findBookById(id);
        log.info("Book found");
        bookRepo.delete(book);
    }

    private BooksResponseEntity makeBooksResponseEntity(Page<Books> Books,List<BooksDto> BooksDto) {
        return BooksResponseEntity.builder()
                .context(BooksDto)
                .PageNumber(Books.getNumber())
                .PageSize(Books.getSize())
                .TotalElements(Books.getTotalElements())
                .TotalPages(Books.getTotalPages())
                .Last(Books.isLast())
                .build();
    }

    private List<BooksDto> mapToBooksDto(List<Books> books){
        return books.stream().map(book->booksToBooksDto(book)).collect(Collectors.toList());
    }

    private void validateBook(BooksDto booksDto){
        log.info("Validating given Book...");
        if(booksDto.getTitle().isEmpty() || booksDto.getAuthor().isEmpty() || booksDto.getDescription().isEmpty() ){
            throw new BookCouldntCreate("Missing Required Fields");
        }
    }

    private Books findBookById(int id) {
        log.info("Trying to find book by id {}", id);
        return bookRepo.findById(id).orElseThrow(()->new BookNotFoundException("Book not found"));
    }

    private void makeBookfromBookDto(BooksDto bookDto,Books book) {
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
    }

}
