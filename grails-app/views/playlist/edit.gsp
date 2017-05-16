<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit Playlist | 8tracks</title>
</head>

<body>
    <g:link action="index" class="action back">Back</g:link>
    <div class="playlists edit">
        <br />
        <h2>Update Playlist</h2>

        <g:form action="update" id="${playlist.id}">

            <g:render template="form" model="[playlist: playlist]" />

            <div class="form-buttons">
                <g:submitButton name="submit" value="Update" />
            </div>

        </g:form>
    </div>
</body>
</html>