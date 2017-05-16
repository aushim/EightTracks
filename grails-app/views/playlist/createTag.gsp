<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add New Tag | 8tracks</title>
</head>

<body>
    <g:link action="showTags" params="[id: playlist.id]" class="action back">Back</g:link>
    <div class="playlists new">
        <br />
        <h2>Create Tag</h2>

        <g:form action="insertTag" params="[playlistId: playlist.id]">

            <g:render template="tagForm" model="[tag: tag]" />

            <div class="form-buttons">
                <g:submitButton name="submit" value="Create" />
            </div>

        </g:form>
    </div>
</body>
</html>