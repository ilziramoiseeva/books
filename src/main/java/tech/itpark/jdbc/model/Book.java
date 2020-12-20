package tech.itpark.jdbc.model;

import lombok.Value;

@Value
public class Book {
    long id;
    int categoryId;
    String name;
    String author;
    int price;
}
