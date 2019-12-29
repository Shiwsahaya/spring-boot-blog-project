<%@ page import="net.blog.post.model.Posts" %>
<%@ page import="java.util.List" %>


<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <title>Title</title>
</head>

<body>

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