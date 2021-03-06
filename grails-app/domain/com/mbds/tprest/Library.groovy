package com.mbds.tprest

class Library {

    String name;
    String address
    Integer yearCreated;

    static hasMany = [books:Book]

    static constraints = {
        name blank : false
        address blank : false
        yearCreated nullable : false
    }

    static mapping = {
        books cascade: "all-delete-orphan"
    }
}
