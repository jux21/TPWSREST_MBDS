package tpwsrest

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/library/${idLib}/books"(controller: "Api", action: "booksInLibrary")
        "/api/library/${idLib}/book/${idBook}"(controller: "Api", action: "bookInLibrary")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
