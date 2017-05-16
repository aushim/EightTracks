<%@ page import="grails.util.Environment" %>
<!DOCTYPE html>
<html>
<head>
    <title>8tracks - Discover new music!</title>
    <style type="text/css" media="screen">
    #page-body {
        margin: 0 1em 1.25em 6em;
        height: 94vh;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    .even {
        float: right;
        padding: 20px;
        width: 30%;
        min-height: 200px;
    }

    .odd-side {
        float: left;
        padding: 20px;
        width: 50%;
        min-height: 200px;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
        margin-left: 20px;
    }

    #controller-list {
        width: 80%;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }
    </style>
</head>

<body>
<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="page-body" role="main">
    <br/>

    <h1>&nbsp;<g:link controller="main" action="index">8tracks Console</g:link></h1>

    <div class="notice">
        <g:if test="${flash.message}">
            ${flash.message}
        </g:if>
    </div>

    <div id="controller-list" role="navigation">
        <ul>
            <li class="controller"><g:link controller="playlist" action="index">Manage Playlists</g:link></li>
            <li class="controller"><g:link controller="exploreAPI">Explore Playlists</g:link></li>
        </ul>
    </div>
</div>
</body>
</html>
