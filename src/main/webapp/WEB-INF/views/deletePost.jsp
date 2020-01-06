<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: shiw
  Date: 25/12/19
  Time: 11:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/posts/delete-confirm" method="post" modelAttribute="posts">
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
            <td><form:input disabled="true" path="title"/></td>
        </tr>

        <tr>
            <td>Body:</td>
            <td><form:textarea disabled="true" rows="4" cols="50" path="body"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Are You Sure For Delete?"></td>
        </tr>
    </table>
    <input type="button" onclick="history.back()" value="cancel">
</form:form>
</body>
</html>