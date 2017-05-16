package eighttracks

import grails.rest.RestfulController
import grails.transaction.Transactional

class TagAPIController extends RestfulController<Tag> {

    static responseFormats = ['json']

    TagAPIController() {
        super(Tag)
    }

    @Override
    def index() {
        respond listAllResources(params), [includes: includeFields, excludes: ['class']]
    }

    @Override
    def show() {
        respond Tag.get(params.id)
    }

    @Transactional
    def save(Tag tag) {
        if (tag.hasErrors()) {
            respond tag.errors
        }
        else {
            Playlist playlist = Playlist.get(params.playlistId)
            Tag t = Tag.findByTName(tag.tName)
            if (t == null) {
                t = tag
                t.save(flush: true)
            }
            if (!playlist.tags.contains(t)) {
                playlist.addToTags(t)
            }
            if (playlist.save(flush: true))
            respond playlist
        }
    }

    @Transactional
    def delete(Tag tag) {
        if(tag == null) {
            render status: 404
        }
        else {
            Playlist playlist = Playlist.get(params.playlistAPIId)
            if (playlist.tags.contains(tag)) {
                playlist.removeFromTags(tag)
            }
            playlist.save(flush: true)
            respond playlist
        }
    }

    @Override
    protected List<Tag> listAllResources(Map params) {
        return Playlist.get(params.playlistAPIId).tags.toList()
    }

    @Override
    protected Tag queryForResource(Serializable id) {
        Playlist.get(params.playlistAPIId).tags.find{ it.id == id }
    }

    private getIncludeFields() {
        params.fields?.tokenize('+')
    }
}
