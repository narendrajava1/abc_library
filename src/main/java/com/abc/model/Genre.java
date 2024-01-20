package com.abc.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
@Data
public class Genre {
    @Id
    private String genreID;

    private String genreName;
}
