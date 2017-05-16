package eighttracks

class Song {

    String sName
    String artist
    Date dateCreated
    Date lastUpdated

    static hasMany = [playlists: Playlist]
    static belongsTo = [Playlist]

    static constraints = {
        sName nullable: false, blank: false
        artist nullable: true
    }

}
