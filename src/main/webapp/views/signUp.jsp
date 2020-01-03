<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form action="/sign-up-success" method="post">
    Name:
    <input type="text" name="name"><br>
    Email:
    <input type="email" name="email"><br>
    Password:
    <input type="password" name="password"><br>
    <input type="submit" value="Sign Up">
</form>
</table>
</body>
</html>