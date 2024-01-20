package com.abc.service;

import com.abc.model.Genre;
import com.abc.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;


   public  List<Genre> getAllGenres(){
      return genreRepository.getAllGenres();
    }
    public  List<Genre> getGenreByGenreName(String generes){
        Query query=new Query();
        query.addCriteria(Criteria.where("genreName").in(generes));
      return genreRepository.getGenreByGenreName(query);
    }

    public Genre saveGenre(Genre genere) {
       return genreRepository.saveGenre(genere);
    }
}
