<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Playlist Tags | 8tracks</title>
</head>

<body>

    <g:link action="index" class="action back">Back</g:link>
    <br />
    <div class="tags index">
        <h2>${playlist.pName} - Tags</h2>

        <g:link action="createTag" params="[playlistId: playlist.id]" class="action new">Add New Tag</g:link>

        <div>${tags.size()} ${tags.size() == 1 ? 'tag' : 'tags'} found</div>

        <table class="listing" summary="tag list">
            <tr class="header">
                <th></th>
                <th class="center">Tag</th>
                <th>Actions</th>
            </tr>
            <g:each status="i" in="${tags}" var="tag">
            <tr class="${ (i % 2) == 0 ? 'odd' : 'even'}">
                <td>${i+1}</td>
                <td>${tag?.tName}</td>
                <td class="actions">
                    <g:link action="deleteTag" params="[playlistId: playlist.id, tagId: tag.id]" class="action delete">Delete</g:link>
                </td>
            </tr>
            </g:each>
        </table>

    </div>
</body>
</html>