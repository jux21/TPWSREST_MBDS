package com.mbds.tprest

class Book {

    String name;
    Date releaseDate;
    String isbn;
    String author;

    static belongsTo = [Library]

    static constraints = {
        name blank : false
        releaseDate blank : false
        isbn nullable : false
        author nullable : false
    }
}
