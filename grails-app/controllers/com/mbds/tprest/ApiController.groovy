package com.mbds.tprest

import grails.converters.JSON
import grails.converters.XML
import grails.gorm.transactions.Transactional

class ApiController {

    def index() { }

    @Transactional
    def book() {

        switch(request.getMethod())
        {
            case "POST":
                if(!Library.get(params.library.id)) {
                    render(status: 400, text: "Cannot attach a book to a non existant library "+params.library.id+"\n")
                    return
                }
                def bookInstance = new Book(params)
                if(Library.get(params.library.id).addToBooks(bookInstance).save(flush:true))
                {
                    render(status: 201, text: "Book "+bookInstance.getId()+" created in library "+params.library.id+"\n")
                }
                else
                {
                    render(status: 400, text: "Book not created in library "+params.library.id+"\n")
                }
                break;


            case "GET":
                if(!Book.get(params.id)) {
                    render(status: 400, text: "Cannot find the book "+params.id+"\n")
                    return
                }
                else {
                    Book bookInstance = Book.get(params.id)
                    def responseStatus = 200
                    switch (request.getHeader("Accept"))
                    {
                        case "application/json":
                            render(status: responseStatus, bookInstance as JSON)
                            break
                        case "application/xml":
                            render(status: responseStatus, bookInstance as XML)
                            break
                        default:
                            render(status: responseStatus, bookInstance as JSON)
                            break
                    }
                }
                break;

            case "PUT":
                if(!Library.get(params.library.id)) {
                    render(status: 400, text: "Cannot alter a book to a non existant library "+params.library.id+"\n")
                    return
                }

                Book book = Book.findAllById(params.id).get(0)
                book.setName(params.name)
                book.setIsbn(params.isbn)
                book.setReleaseDate(Date.parse("yyyy-MM-dd", params.releaseDate))
                book.setAuthor(params.author)
                Library.get(params.library.id).removeFromBooks(book)
                Library.get(params.library.id).addToBooks(book)
                if(book.save(flush:true))
                {
                    render(status: 200, text: "Book "+book.getId()+" updated in library "+params.library.id+"\n")
                }
                else
                {
                    render(status: 400, text: "Book not updated in library "+params.library.id+"\n")
                }
                break;

            case "DELETE":
                if(!Book.get(params.id)) {
                    render(status: 400, text: "Cannot find the book "+params.id+"\n")
                    return
                }
                else {
                    Book.get(params.id).delete(flush:true)
                    render(status: 200, text: "Book "+params.id+" deleted\n")
                    break;
                }
                break;

            default:
                response.status = 405
                break;
        }
    }

    def library() {

        switch(request.getMethod())
        {
            case "POST":
                def libraryInstance = new Library(params)
                if(libraryInstance.save(flush:true))
                {
                    render(status: 201, text: "Library "+libraryInstance.getId()+" created\n")
                }
                else
                {
                    render(status: 400, text: "Library not created\n")
                }
                break;

            case "GET":
                if(!Library.get(params.id)) {
                    render(status: 400, text: "Cannot find the library "+params.id+"\n")
                    return
                }
                else {
                    Library libraryInstance = Library.get(params.id)
                    def responseStatus = 200
                    switch (request.getHeader("Accept"))
                    {
                        case "application/json":
                            render(status: responseStatus, libraryInstance as JSON)
                            break
                        case "application/xml":
                            render(status: responseStatus, libraryInstance as XML)
                            break
                        default:
                            render(status: responseStatus, libraryInstance as JSON)
                            break
                    }
                }
                break;

            case "PUT":
                if(!Library.get(params.id)) {
                    render(status: 400, text: "Cannot find the library "+params.id+"\n")
                    return
                }

                Library library = Library.findAllById(params.id).get(0)
                library.setName(params.name)
                library.setAddress(params.address)
                library.setYearCreated(params.yearCreated as int)
                if(library.save(flush:true))
                {
                    render(status: 200, text: "Library "+params.id+" updated\n")
                }
                else
                {
                    render(status: 400, text: "Library "+params.id+" not updated\n")
                }
                break;

            case "DELETE":
                if(!Library.get(params.id)) {
                    render(status: 400, text: "Cannot find the library "+params.id+"\n")
                    return
                }
                else {
                    Library.get(params.id).delete(flush:true)
                    render(status: 200, text: "library "+params.id+" deleted\n")
                    break;
                }
                break;

            default:
                response.status = 405
                break;
        }
    }


    def libraries() {

        switch(request.getMethod())
        {
            case "DELETE":
                Library.findAll().each { it.delete(flush: true) }
                render(status: 200, text: "All libraries deleted\n")
            break;

            case "GET":
                List<Library> librariesInstances = Library.getAll()
                def responseStatus = 200
                switch (request.getHeader("Accept"))
                {
                    case "application/json":
                        render(status: responseStatus, librariesInstances as JSON)
                        break
                    case "application/xml":
                        render(status: responseStatus, librariesInstances as XML)
                        break
                    default:
                        render(status: responseStatus, librariesInstances as JSON)
                        break
                }

            default:
                response.status = 405
                break;
        }
    }

    def books() {

        switch(request.getMethod())
        {
            case "DELETE":
                Book.findAll().each { it.delete(flush: true) }
                render(status: 200, text: "All books deleted\n")
            break;

            case "GET":
                List<Book> BooksInstances = Book.getAll()
                def responseStatus = 200
                switch (request.getHeader("Accept"))
                {
                    case "application/json":
                        render(status: responseStatus, BooksInstances as JSON)
                        break
                    case "application/xml":
                        render(status: responseStatus, BooksInstances as XML)
                        break
                    default:
                        render(status: responseStatus, BooksInstances as JSON)
                        break
                }

            default:
                response.status = 405
                break;
        }
    }

    def booksInLibrary() {

        switch(request.getMethod())
        {
            case "GET":
                Set<Book> BooksInLibInstances = Library.findById(params.idLib).getBooks()
                def responseStatus = 200
                switch (request.getHeader("Accept"))
                {
                    case "application/json":
                        render(status: responseStatus, BooksInLibInstances as JSON)
                        break
                    case "application/xml":
                        render(status: responseStatus, BooksInLibInstances as XML)
                        break
                    default:
                        render(status: responseStatus, BooksInLibInstances as JSON)
                        break
                }


            default:
                response.status = 405
                break;
        }
    }

    def bookInLibrary() {

        switch(request.getMethod())
        {
            case "GET":
                if(!Library.get(params.idLib)) {
                    render(status: 404, text: "Cannot get a book from a non existant library (${params.idLib})\n")
                    return
                }
                if(!Library.get(params.idLib).getBooks().sort{ it.id }.getAt(params.idBook as int)) {
                    render(status: 404, text: "Non existant book (${params.idBook}) in library (${params.idLib})\n")
                    return
                }
                Book BookInLibInstance = Library.get(params.idLib).getBooks().sort{ it.id }.getAt(params.idBook as int)
                def responseStatus = 200
                switch (request.getHeader("Accept"))
                {
                    case "application/json":
                        render(status: responseStatus, BookInLibInstance as JSON)
                        break
                    case "application/xml":
                        render(status: responseStatus, BookInLibInstance as XML)
                        break
                    default:
                        render(status: responseStatus, BookInLibInstance as JSON)
                        break
                }
                break
            case "POST":
                if(!Library.get(params.idLib)) {
                    render(status: 404, text: "Cannot set a book to a non existant library "+params.idLib+"\n")
                    return
                }
                if(Library.get(params.idLib).getBooks().sort{ it.id }.getAt(params.idBook as int)) {
                    render(status: 404, text: "Book "+params.idBook+" already exists in library "+params.idLib+"\n")
                    return
                }
                def bookInstance = new Book(params)
                Library.get(params.idLib).addToBooks(bookInstance)
                if(bookInstance.save(flush:true))
                {
                    render(status: 201, text: "Book "+params.idBook+" created in library "+params.idLib+"\n")
                }
                else
                {
                    render(status: 400, text: "Book "+params.idBook+" not created in library "+params.idLib+"\n")
                }
                break;

            default:
                response.status = 405
                break;
        }
    }
}
