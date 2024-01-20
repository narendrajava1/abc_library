package com.abc.service;


import com.abc.model.Book;
import com.abc.repository.BookRepository;
import com.mongodb.DBRef;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Book saveBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }

    public List<Book> getBookById(String bookId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(bookId));
        return mongoTemplate.find(query, Book.class);
    }

    @Override
    public Optional<List<Book>> getAllBooks() {
        return Optional.ofNullable(mongoTemplate.findAll(Book.class));
    }

    @Override
    public String updateBook(Book newBook) {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(new ObjectId(newBook.getId())));
        Update update = new Update();
        update.set("isActive", newBook.isActive());
        update.set("isAvailable", newBook.isAvailable());

        return  mongoTemplate.findAndModify(query,update,Book.class).getId();
    }

    @Override
    public List<Book> getBookByGenreId(String genreID) {

//        Query query=new Query();
//        ObjectId oid = new ObjectId(genreID);
//
//        Criteria criteria = Criteria.where("genre").is(new DBRef("abc_db","genres",oid));
//
//        query.addCriteria(criteria);

        return mongoTemplate.find(new Query(Criteria.where("genre.$id").is(new ObjectId(genreID))),Book.class);
    }
}