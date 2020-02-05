<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: shiw
  Date: 25/12/19
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>User: <security:authentication property="principal.username"/>
    <br>
    Role(s): <security:authentication property="principal.authorities"/>
</p>
<form:form action="/posts/save" method="post" modelAttribute="posts">

    <tr>
            <%--@elvariable id="category" type="java"--%>
        <input type="checkbox" name="name" value="Technology"/>Technology
        <input type="checkbox" name="name" value="Science"/>Science
        <input type="checkbox" name="name" value="Nature"/>Nature
        <input type="checkbox" name="name" value="Space"/>Space
    </tr>
    <table border="0" cellpadding="5">
        <tr>
            <td>ID:</td>
            <td>
                    ${posts.id}
                <form:hidden path="id"/>
            </td>
        </tr>

        <tr>
            <td>Title:</td>
            <td><form:input path="title"/></td>
        </tr>

        <tr>
            <td>Body:</td>
            <td><form:textarea rows="4" cols="50" path="body"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="save"></td>
        </tr>
    </table>
</form:form>
</body>
</html>