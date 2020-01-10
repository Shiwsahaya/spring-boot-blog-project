<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: shiw
  Date: 25/12/19
  Time: 12:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

</head>
<body>
<h2 style="text-align: center">New Post</h2><br/>

<div class="d-flex justify-content-center align-items-center container ">
    <div class="row ">
<form:form action="save" method="post" modelAttribute="posts">

    <h4>Choose Category</h4>
    <div class="form-group form-check">
        <input type="checkbox" class="form-check-input" id="tech-checkbox" name="name" value="Technology">
        <label class="form-check-label" for="tech-checkbox">Technology</label><br>

        <input type="checkbox" class="form-check-input" id="science-checkbox" name="name" value="Science">
        <label class="form-check-label" for="science-checkbox">Science</label><br>

        <input type="checkbox" class="form-check-input" id="nature-checkbox" name="name" value="Nature">
        <label class="form-check-label" for="nature-checkbox">Nature</label><br>

        <input type="checkbox" class="form-check-input" id="space-checkbox" name="name" value="Space">
        <label class="form-check-label" for="space-checkbox">Space</label><br>
    </div>

<div class="form-group">
    <label for="title-id">Title</label>
    <form:input path="title" type="text" class="form-control" id="title-id" aria-describedby="emailHelp"/>
</div>
    <div class="form-group">
        <label for="textarea">Body</label>
        <form:textarea path="body" class="form-control rounded-0" id="textarea" rows="4" cols="31"/>
    </div>
<button type="submit" class="btn btn-primary">Submit</button>
</form:form>
</div>
</div>
</body>
</html>