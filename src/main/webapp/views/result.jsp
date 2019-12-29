<%@ page import="net.blog.post.model.Posts" %>
<%@ page import="java.util.List" %><%--
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
<h3><a href="add">New Post</a></h3>
<h3><a href="sort-by-published-date">Sort BY Published Date</a></h3>
<h3><a href="sort-by-last-updated">Sort By Last Updated</a></h3>

<form method="post" action="filter">
    <input type="checkbox" name="name" value="1"/>Technology
    <input type="checkbox" name="name" value="2"/>Science
    <input type="checkbox" name="name" value="3"/>Nature
    <input type="checkbox" name="name" value="4"/>Space
    <input type="submit" value="Submit">
</form>

<form method="post" action="search">
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
</body>
</html>