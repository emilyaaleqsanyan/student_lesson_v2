<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>

</head>
<body>
<% if(session.getAttribute("msg")!= null){%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<% session.removeAttribute("msg");%>
<%}%>
<form action="/login" method="post">
  email: <input type="text" name="email"><br>
  password: <input type="password" name="password"><br>
  <input type="submit" value="login">
</form>
<br>
<br>

<a href="/register">Register</a>

</body>
</html>