package eighttracks

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->

        JSON.registerObjectMarshaller( Playlist ) { Playlist playlist ->
            return [
                    id : playlist?.id,
                    name: playlist?.pName,
                    description: playlist?.description,
                    plays: playlist?.plays,
                    likes: playlist?.likes
            ]
        }

        JSON.registerObjectMarshaller( Tag ) { Tag tag ->
            return [
                    id     : tag?.id,
                    name   : tag?.tName
            ]
        }

    }
    def destroy = {
    }


}
