<%@ page import="eighttracks.Playlist" %>
<g:render template="/shared/errorMessages" model="[object: playlist]" />

<table summary="Playlist form fields">
    <tr>
        <th><label for="pName">Name</label></th>
        <td><g:textField name="pName" value="${playlist.pName}" /></td>
    </tr>
    <tr>
        <th><label for="description">Description</label></th>
        <td><g:textField name="description" value="${playlist.description}" /></td>
    </tr>
</table>