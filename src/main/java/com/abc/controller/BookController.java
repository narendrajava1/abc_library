package com.abc.controller;

import com.abc.exception.ResourceNotFoundException;
import com.abc.model.Book;
import com.abc.model.Genre;
import com.abc.service.BookService;
import com.abc.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private GenreService genreService;

    @PostMapping(value = "save", consumes = "application/json")
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        logger.info("Entering the save book {}",book);
        try {
            if (book != null) {
                // Save the genre
            book.setGenre(genreService.saveGenre(book.getGenre()));
            // Save the book
            Book createdBook = bookService.saveBook(book);
            return new ResponseEntity<>(createdBook, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            logger.error("Exception while saving the book {}",book);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/by")
    public ResponseEntity<List<Book>> getBookById(@RequestParam String bookId) {
        logger.info("Entering into the getBookById {}",bookId);
        return new ResponseEntity<>(Optional.ofNullable(bookService.getBookById(bookId))
                .orElseThrow(()->new ResourceNotFoundException(1234,"there are no books for this id"+bookId)), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        logger.info("Entering into the getAllBooks");
        return new ResponseEntity<>(bookService.getAllBooks()
                .orElseThrow(()->new ResourceNotFoundException(1234,"there are no books")), HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<String> updateBook(@RequestBody  Book book){
        logger.info("Entering into the updateBook {}",book);
        return new ResponseEntity<>(bookService.updateBook(book),HttpStatus.OK);
    }

    @GetMapping("get/book/genres/names")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestBody List<String> genresNames) {
        logger.info("Entering into the getAllGenres");

        List<String> genreIdList = genreService.getGenreByGenreName(genresNames).stream().map(Genre::getGenreID).collect(Collectors.toList());
        List<Book> bookList = bookService.getBookByGenreId(genreIdList);
        return new ResponseEntity<>(Optional.ofNullable(bookList)
                .orElse(Collections.emptyList()), HttpStatus.OK);
    }


}
