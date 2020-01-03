<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<form:form action="/save" method="post" modelAttribute="posts">

    <tr>
            <%--@elvariable id="category" type="java"--%>
        <input type="checkbox" name="name" value="1"/>Technology
        <input type="checkbox" name="name" value="2"/>Science
        <input type="checkbox" name="name" value="3"/>Nature
        <input type="checkbox" name="name" value="4"/>Space
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