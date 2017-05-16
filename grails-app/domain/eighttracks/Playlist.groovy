package eighttracks

class Playlist {

    String pName
    String description
    Integer plays = 0
    Integer likes = 0
    Date dateCreated
    Date lastUpdated

    static hasMany = [tags: Tag, songs: Song]

    static mapping = {
        id generator: 'sequence', params: [sequence: 'playlist_id_seq']
        plays default: 0
        likes default: 0
    }

    static constraints = {
        pName nullable: false, blank: false
        description nullable: true
        plays nullable: false
        likes nullable: false
    }
}
