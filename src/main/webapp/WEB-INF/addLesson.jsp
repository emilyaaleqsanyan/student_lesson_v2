<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 13.01.2024
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
</head>
<body>

<% if(session.getAttribute("msg")!= null){%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<% session.removeAttribute("msg");%>
<%}%>
<br>
<br>

Add Lesson<br>
<form method="post" action="/addLesson">
    Lesson Name:<input type="text" name="lessonName"><br>
    Lesson Duration:<input type="number" name="lessonDuration"><br>
    Lecturer Name:<input type="text" name="lecturerName"><br>
    Price:<input type="number" name="price"><br>
    <input type="submit" value="add">
</form>

</body>
</html>
