<g:render template="/shared/errorMessages" model="[object: tag]" />

<table summary="Address form fields">
    <tr>
        <th><label for="tName">Tag</label></th>
        <td><g:textField name="tName" value="${tag?.tName}" /></td>
    </tr>
</table>