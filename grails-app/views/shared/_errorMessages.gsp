<g:if test="${object && object.hasErrors()}">
    <br />
    <div id="errorExplanation">
        <h2>${object.errors.getErrorCount()} ${object.errors.getErrorCount() > 1 ? 'errors' : 'error'} prohibited this record from being saved</h2>
        <p>There were problems with the following fields:</p>
        <ul>
            <g:eachError bean="${object}">
                <li><g:message error="${it}" /></li>
            </g:eachError>
        </ul>
    </div>
</g:if>