package eighttracks

import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class PlaylistController {

    static layout = 'admin'

    def index() {
        int max = 30
        int offset = 0
        String query = null
        int count = 0
        if (params.max) {
            max = params.max.toInteger()
        }
        if (params.offset) {
            offset = params.offset.toInteger()
        }
        if (params.query) {
            query = params.query
        }
        List<Playlist> playlists = new ArrayList<Playlist>()
        if (query) {
            count = Playlist.countByPNameIlike("%${query}%")
            int fromIndex = offset
            int toIndex = (offset + max > count) ? (count) : (offset + max)
            playlists = Playlist.findAllByPNameIlike("%${query}%", [sort: "lastUpdated"]).asList().reverse().subList(fromIndex, toIndex)
        }
        else {
            count = Playlist.count()
            playlists = Playlist.list(sort: "lastUpdated", order: "desc",  max: max, offset: offset)
        }
        [playlists: playlists, max: max, offset: offset, query: query, count: count]
    }

    def create() {
        Playlist playlist = new Playlist()
        [playlist: playlist]
    }

    def save() {
        Playlist playlist = new Playlist()
        playlist.properties = params
        if (playlist.save(flush: true)) {
            flash.message = "Playlist created successfully"
            redirect(action: 'index')
        }
        else {
            render(view: 'create', model: [playlist: playlist])
        }
    }

    def edit() {
        [playlist: Playlist.get(params.id)]
    }

    def show() {
        [playlist: Playlist.get(params.id)]
    }

    def update() {
        Playlist playlist = Playlist.get(params.id)
        playlist.properties = params
        if (playlist.save(flush: true)) {
            flash.message = "Playlist updated successfully"
            redirect(action: 'index', params: [id: params.id])
        }
        else {
            render(view: 'edit', model: [playlist: playlist])
        }
    }

    def delete() {
        Playlist playlist = Playlist.get(params.id)
        playlist.delete(flush: true)
        flash.message = "Playlist deleted successfully"
        redirect(action: 'index')
    }

    def showTags() {
        Playlist playlist = Playlist.get(params.id)
        [playlist: playlist, tags: playlist?.tags]
    }

    def createTag() {
        Playlist playlist = Playlist.get(params.playlistId)
        [playlist: playlist, tag: new Tag()]
    }

    def insertTag() {
        Playlist playlist = Playlist.get(params.playlistId)
        Tag tag = Tag.findByTName(params.tName)
        if (tag == null) {
            tag = new Tag(params)
            tag.save(flush: true)
        }
        if (!playlist.tags.contains(tag)) {
            playlist.addToTags(tag)
        }
        if (playlist.save(flush: true)) {
            flash.message = "Tag added successfully"
            redirect(action: 'showTags', params: [id: playlist.id])
        }
        else {
            render(view: 'createTag', model: [playlist: playlist, tag: tag])
        }
    }

    def deleteTag() {
        Playlist playlist = Playlist.get(params.playlistId)
        Tag tag = Tag.get(params.tagId)
        if (playlist.tags.contains(tag)) {
            playlist.removeFromTags(tag)
        }
        playlist.save(flush: true)
        flash.message = "Tag removed successfully"
        redirect(action: 'showTags', params: [id: playlist.id])
    }

}
