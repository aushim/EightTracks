<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Playlists | 8tracks</title>
</head>

<body>
<g:form action="index">
    <g:textField name="query" value="${query}" /><g:submitButton name="submit" value="Search" />
</g:form>
<br />
    <a href="${createLink(uri: '/', absolute: true)}" class="action back">Back</a>
    <br />
    <div class="playlists index">
        <h2>Playlists</h2>
        <g:link action="create" class="action new">Add New Playlist</g:link>

        <div>Showing ${offset + 1}-${offset + playlists.size()} of ${count} ${count == 1 ? 'playlist' : 'playlists'}</div>

        <br />
        <table class="listing" summary=Playlist list">
            <tr class="header">
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Plays</th>
                <th>Likes</th>
                <th>Tags</th>
                <th>Actions</th>
            </tr>
            <g:each status="i" in="${playlists}" var="playlist">
            <tr class="${ (i % 2) == 0 ? 'odd' : 'even'}">
                <td>${playlist?.id}</td>
                <td>${playlist?.pName}</td>
                <td>${playlist?.description}</td>
                <td>${playlist?.plays}</td>
                <td>${playlist?.likes}</td>
                <td>${playlist?.getTags()?.tName?.join('|')}</td>
                <td class="actions">
                    <g:link action="showTags" id="${playlist.id}" class="action edit">Manage Tags</g:link>
                    <g:link action="edit" id="${playlist.id}" class="action edit">Edit</g:link>
                    <g:link action="delete" id="${playlist.id}" class="action delete">Delete</g:link>
                </td>
            </tr>
            </g:each>
        </table>
        <br />
        <g:if test="${offset >= max}">
            <g:link action="index" class="action back" params="[query: query, max: max, offset: offset-max]">Previous</g:link>
        </g:if>
        <g:if test="${offset + playlists.size() < count}">
            <g:link action="index" class="action back" params="[query: query, max: max, offset: offset+max]">Next</g:link>
        </g:if>
    </div>
</body>
</html>