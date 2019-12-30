<%@ page import="net.blog.post.model.Posts" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<%--
  Created by IntelliJ IDEA.
  User: shiw
  Date: 24/12/19
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<a href="add">New Post</a>
<a href="/sort-by-published-date">Sort BY Published Date</a>|
<a href="/sort-by-last-updated">Sort By Last Updated</a>

<form method="post" action="/filter">
    <input type="checkbox" name="name" value="1"/>Technology
    <input type="checkbox" name="name" value="2"/>Science
    <input type="checkbox" name="name" value="3"/>Nature
    <input type="checkbox" name="name" value="4"/>Space
    <input type="submit" value="Filter">
</form>

<form method="post" action="/search">
    <input type="text" name="keyword"/>
    <input type="submit" value="Search"/>
</form>

<% List<Posts> results = (List<Posts>) request.getAttribute("listPost");%>
<% for (Posts value : results) {%>
<h2>
    <%= value.getTitle()%>
</h2>
<%= value.getBody()%>
<br/>
<a href="edit/<%=value.getId()%>">Edit</a><br>
<a href="delete/<%=value.getId()%>">Delete</a>
<%}%>

<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="/page/1">1</a></li>
        <li class="page-item"><a class="page-link" href="/page/2">2</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
</nav>

</body>
</html>