package com.abc.service;


import com.abc.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book saveBook(Book book);

    List<Book> getBookById(String bookId);
    Optional<List<Book>> getAllBooks();

    String updateBook(Book book);

    List<Book> getBookByGenreId(List<String> genreID);
}
