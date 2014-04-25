<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Projects</title>
</head>

<body>
    <table>
    <% projects.each { currProject -> %>
        <tr><td><g:link action="show" id="${currProject.id}">${currProject}</g:link></td></tr>
    <%}%>
    </table>

</body>
</html>