package com.abc.repository;

import com.abc.model.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

   public  List<Genre> getAllGenres(){
        return mongoTemplate.findAll(Genre.class);
    }

    public List<Genre> getGenreByGenreName(Query query) {
       return mongoTemplate.find(query,Genre.class);
    }

    public Genre saveGenre(Genre genre) {
       return mongoTemplate.insert(genre);
    }
}
