package com.abc.controller;

import com.abc.model.Genre;
import com.abc.service.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class GenreController {

    Logger logger = LoggerFactory.getLogger(GenreController.class);

    @Autowired
    private GenreService genreService;


    @PostMapping("by/genres")
    public ResponseEntity<List<Genre>> getGenreByGenreName(@RequestBody List<String> genres) {
        logger.info("Entering into the getBookByGenre {}",genres.toArray());
        return new ResponseEntity<>(Optional.ofNullable(genreService.getGenreByGenreName(genres))
                .orElse(Collections.emptyList()), HttpStatus.OK);
    }

    @GetMapping("genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        logger.info("Entering into the getAllGenres");
        return new ResponseEntity<>(Optional.ofNullable(genreService.getAllGenres())
                .orElse(Collections.emptyList()), HttpStatus.OK);
    }
}
