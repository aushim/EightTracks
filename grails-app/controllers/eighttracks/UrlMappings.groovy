package eighttracks

class UrlMappings {

    static mappings = {

        "/"(controller: "main", action: 'index')
        "500"(view: '/error')
        "404"(view:'/notFound')

        "/api/playlist"(resources: "playlistAPI") {
            "/tag"(resources:"tagAPI")
        }
        "/api/explore"(controller: "exploreAPI")

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }
    }
}
