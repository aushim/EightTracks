package eighttracks

class ExploreAPIController {

    static responseFormats = ['json']

    def index() {
        List<String> tagList = new ArrayList<String>();
        boolean noQuery = false
        if (params.tags) {
            tagList = params.tags.toString().split(";").toList().collect { it.toLowerCase() }
        }
        int max_p = 50
        int offset_p = 0
        if (params.max) {
            max_p = params.max.toInteger()
        }
        if (params.offset) {
            offset_p = params.offset.toInteger()
        }


        def tResults = []
        def pll = []
        if (tagList.size() == 0) {
            noQuery = true
            tagList = Tag.list().tName
        }

        def p = Playlist.createCriteria()
        def pResults = p.list(max: max_p, offset: offset_p) {
            tags {
                inList('tName', tagList)
            }
            projections {
                groupProperty('id')
                property('pName')
                property('description')
                property('plays')
                property('likes')
                rowCount('frequency')
            }
            order('frequency', 'desc')
            order('likes', 'desc')
            order('plays', 'desc')
        }
        pResults.each {
            def pl = [:]
            pl['id'] = it[0]
            pl['name'] = it[1]
            pl['artist'] = it[2]
            pl['plays'] = it[3]
            pl['likes'] = it[4]
            pl['tags'] = Playlist.get(it[0]).tags.tName
            pll.push(pl)
        }
        def pIds = pll?.collect { it['id'] }

        if (pResults) {
            int max_t = 10
            if (params.tagCount) {
                max_t = params.tagCount.toInteger()
            }

            if (noQuery) {
                tagList = ['']
            }

            def t = Tag.createCriteria()
            tResults = t.list {
                not {
                    inList('tName', tagList )
                }
                playlists {
                    inList('id', pIds)
                }
                projections {
                    groupProperty('id')
                    property('tName')
                    rowCount('frequency')
                }
                maxResults( max_t )
                order('frequency', 'desc')
            }
        }

        def o = [:]
        o['relatedTags'] = tResults?.collect { it[1] }
        o['playlists'] = pll
        respond o
    }
}
