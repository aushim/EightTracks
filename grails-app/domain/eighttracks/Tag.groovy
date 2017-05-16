package eighttracks

class Tag {

    String tName
    Date dateCreated
    Date lastUpdated

    static hasMany = [playlists: Playlist]
    static mapping = {
        id generator: 'sequence', params: [sequence: 'tag_id_seq']
        tName index: true
    }
    static belongsTo = [Playlist]
    static constraints = {
        tName nullable: false, blank: false
    }
}
