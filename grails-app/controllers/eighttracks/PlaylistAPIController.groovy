package eighttracks

import grails.transaction.*
import grails.rest.RestfulController

class PlaylistAPIController extends RestfulController<Playlist> {

    static responseFormats = ['json']

    PlaylistAPIController() {
        super(Playlist)
    }

    @Transactional
    def save(Playlist playlist) {

        if (playlist.hasErrors()) {
            respond playlist.errors
        }
        else {
            playlist.save flush: true
            respond playlist
        }
    }

    @Transactional
    def update(Playlist playlist) {
        if(playlist == null) {
            render status: 404
        }
        else {
            if(playlist.hasErrors()) {
                respond playlist.errors
            }
            else {
                playlist.save flush:true
                respond playlist
            }
        }
    }

    @Transactional
    def delete(Playlist playlist) {
        if(playlist == null) {
            render status: 404
        }
        else {
            playlist.delete flush:true
            respond playlist
        }
    }

    @Override
    def index() {
        respond listAllResources(params), [includes: includeFields, excludes: ['class', 'tags']]
    }

    @Override
    protected List<Playlist> listAllResources(Map params) {
        int max = 50
        int offset = 0
        if (params.max) {
            max = params.max.toInteger()
        }
        if (params.offset) {
            offset = params.offset.toInteger()
        }
        return Playlist.list(sort: "id", max: max, offset: offset)
    }

    private getIncludeFields() {
        params.fields?.tokenize('+')
    }
}
