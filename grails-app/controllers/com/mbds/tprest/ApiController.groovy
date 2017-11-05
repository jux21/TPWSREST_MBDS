package com.mbds.tprest

import grails.converters.JSON
import grails.converters.XML

class ApiController {

    def index() { }

    def book() {

        switch(request.getMethod())
        {
            case "POST":
                if(!Library.get(params.library.id)) {
                    render(status: 400, text: "Cannot attach a book to a non existant library (${params.library.id})")
                    return
                }
                def bookInstance = new Book(params)
                if(bookInstance.save(flush:true))
                {
                    response.status = 201
                }
                else
                {
                    response.status = 400
                }
                break;


            case "GET":
                if(!Book.get(params.id)) {
                    //render(status: 400, text: "Cannot find the book ${params.book.id} in Library (${params.library.id})\n")
                    render(status: 400, text: "Cannot find the book ${params.id}\n")
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

            default:
                response.status = 405
                break;
        }
    }

    def library() {

        switch(request.getMethod())
        {
            case "POST":
                if(!Library.get(params.library.id)) {
                    render(status: 400, text: "Cannot find the library (${params.library.id})")
                    return
                }
                def libraryInstance = new Library(params)
                if(libraryInstance.save(flush:true))
                {
                    response.status = 201
                }
                else
                {
                    response.status = 400
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

            default:
                response.status = 405
                break;
        }
    }
}
