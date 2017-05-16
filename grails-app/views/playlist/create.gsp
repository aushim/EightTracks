<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create Playlist | 8tracks</title>
</head>

<body>
    <g:link action="index" class="action back">Back</g:link>
    <div class="playlists edit">
        <br />
        <h2>Create Playlist</h2>

        <g:form action="save">

            <g:render template="form" model="[playlist: playlist]" />

            <div class="form-buttons">
                <g:submitButton name="submit" value="Create" />
            </div>

        </g:form>
    </div>
</body>
</html>