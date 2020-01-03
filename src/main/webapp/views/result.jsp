<%@ page import="net.blog.post.model.Posts" %>
<%@ page import="java.util.List" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
      integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<%--
  Created by IntelliJ IDEA.
  User: shiw
  Date: 24/12/19
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/views/navbar.jsp"/>


<% List<Posts> results = (List<Posts>) request.getAttribute("listPost");%>
<% for (Posts value : results) {%>
<div  class="card bg-light mb-3" style="max-width: 40rem; margin: 1% auto; float: none; text-align: center">
    <div class="card-header"><h6><%= value.getTitle()%></h6></div>
    <div class="card-body">
        <p class="card-text"><%= value.getBody()%></p>
        <a href="/edit/<%=value.getId()%>"><i class="far fa-edit"></i></a>|
        <a href="/delete/<%=value.getId()%>"><i class="far fa-trash-alt"></i></a>
    </div>
</div>
<%}%>

<div style="text-align: center;  margin-left: 40%; margin-top: 1%">
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        <li class="page-item"><a class="page-link" href="/page/1">1</a></li>
        <li class="page-item"><a class="page-link" href="/page/2">2</a></li>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
</nav>
</div>
</body>
</html>