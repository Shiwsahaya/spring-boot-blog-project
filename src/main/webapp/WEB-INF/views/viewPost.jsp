<%@ page import="net.blog.post.model.Posts" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/externalLink.jsp"/>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<body>
<% Posts posts =(Posts) request.getAttribute("viewPost");%>
<div class="card bg-light mb-3" style="max-width: 40rem; margin: 1% auto; float: none; text-align: center">
    <div class="card-header"><h6><%= posts.getTitle()%>
    </h6></div>
    <div class="card-body">
        <p class="card-text"><%=posts.getBody()%></p>
    </div>
</div>
<button type="button" name="back" onclick="history.back()">back</button>
</body>
</html>
