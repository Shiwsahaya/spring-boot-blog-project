<%@ page import="net.blog.post.exception.PostsErrorResponse" %><%--
  Created by IntelliJ IDEA.
  User: shiw
  Date: 09/01/20
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/externalLink.jsp"/>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<% PostsErrorResponse postsErrorResponse= (PostsErrorResponse) request.getAttribute("exceptionMessage");%>
<p><%=postsErrorResponse.getMessage()%></p>
</body>
</html>
