package com.abc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Represents a Book record in the database
 *
 * @author Narendra Kumar Kolli
 */
@Data
@Document(collection = "books")
public class Book {

    /**
     * Unique identification number
     */
    @Id
    private String id;

    /**
     * The title of the book.
     */
    @Field(name = "title")
    private String title;

    /**
     * The author of the book.
     */
    @Field(name = "author")
    private String author;


    /**
     * The isActive status. Determines whether any operations can be performed on
     * the book.
     */
    private boolean isActive;

    /**
     * The isAvailable status. Determines whether the book can be borrowed.
     */
    private boolean isAvailable;

    /**
     * The name of the publisher
     */
    @Field(name = "publisher")
    private String publisher;

    /**
     * The language of the book
     */
    @Field(name = "language")
    private String language;

    /**
     * The number of book pages.
     */
    @Field(name = "pages")
    private int pages;

    /**
     * The date of added
     */
    @Field(name = "date_of_added")
    private Date dateOfAdded;

    @DBRef
    private Genre genre ;


}