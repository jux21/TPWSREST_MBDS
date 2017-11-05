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
                    render(status: 400, text: "Cannot attach a book to a non existant library (${param.library.id})")
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
                    render(status: 400, text: "Cannot find the library (${param.library.id})")
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
