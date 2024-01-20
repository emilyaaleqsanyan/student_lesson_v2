<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19.01.2024
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<form action="/register" method="post">
    name: <input type="text" name="name"><br>
    surname: <input type="text" name="surname"><br>
    email: <input type="text" name="email"><br>
    password: <input type="password" name="password"><br>
    <input type="submit" name="register">
</form>
</body>
</html>
