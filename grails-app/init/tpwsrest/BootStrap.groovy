package tpwsrest

import com.mbds.tprest.Book
import com.mbds.tprest.Library

class BootStrap {

    def init = { servletContext ->

        Library library = new Library(name: 'Lib1', address: 'Roman street', yearCreated: 2002)
                .addToBooks(new Book(name: 'livre1', releaseDate: Date.parse("yyyy-MM-dd", "2017-04-02"), isbn: 'AUG6', author: 'Cesar'))
                .addToBooks(new Book(name: 'livre2', releaseDate: Date.parse("yyyy-MM-dd", "2016-01-22"), isbn: 'GUG7', author: 'Julien'))
                .addToBooks(new Book(name: 'livre3', releaseDate: Date.parse("yyyy-MM-dd", "2017-04-02"), isbn: 'AUG66', author: 'Julius'))
                .addToBooks(new Book(name: 'livre4', releaseDate: Date.parse("yyyy-MM-dd", "2016-01-22"), isbn: 'GUG77', author: 'Brutus'))
                .addToBooks(new Book(name: 'livre5', releaseDate: Date.parse("yyyy-MM-dd", "2017-04-02"), isbn: 'AUG69', author: 'Bonus'))
                .addToBooks(new Book(name: 'livre6', releaseDate: Date.parse("yyyy-MM-dd", "2016-01-22"), isbn: 'GUG70', author: 'Caillus'))
                .save(flush:true)


        Library library2 = new Library(name: 'Lib2', address: 'Turkish street', yearCreated: 2012)
                .addToBooks(new Book(name: 'livre7', releaseDate: Date.parse("yyyy-MM-dd", "2001-02-19"), isbn: 'KK6', author: 'Patrick'))
                .addToBooks(new Book(name: 'livre8', releaseDate: Date.parse("yyyy-MM-dd", "2001-11-22"), isbn: 'LOG7', author: 'Gayardon'))
                .addToBooks(new Book(name: 'livre9', releaseDate: Date.parse("yyyy-MM-dd", "1941-02-19"), isbn: 'PMG6', author: 'Frederic'))
                .addToBooks(new Book(name: 'livre10', releaseDate: Date.parse("yyyy-MM-dd", "1946-11-22"), isbn: 'HG7', author: 'Robert'))
                .addToBooks(new Book(name: 'livre11', releaseDate: Date.parse("yyyy-MM-dd", "1952-02-19"), isbn: 'LG6', author: 'Hinderburg'))
                .addToBooks(new Book(name: 'livre12', releaseDate: Date.parse("yyyy-MM-dd", "2004-11-22"), isbn: 'HJ8', author: 'Blaskowitz'))
                .save(flush:true)

    }

    def destroy = {

    }
}
