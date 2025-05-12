package com.BookshelfApi.api.Repository;

import com.BookshelfApi.api.Models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BookRepo extends JpaRepository<Books, Integer> {


}
