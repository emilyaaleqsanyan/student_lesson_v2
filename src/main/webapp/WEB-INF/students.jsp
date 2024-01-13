<%@ page import="com.example.student_lesson.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.student_lesson.model.Lesson" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.01.2024
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
</head>
<body>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

Students | <a href="/addStudent">Add Student</a>

<%
    if (students!=null && !students.isEmpty()) {%>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Lesson Name</th>
        <th>Delete</th>
    </tr>
    <%
        for (Student student : students){ %>
    <tr>
        <td><%=student.getId()%></td>
        <td><%=student.getName()%></td>
        <td><%=student.getSurname()%></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getLesson().getName()%></td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a></td>
    </tr>
    <%}
    %>
</table>
<%}%>
</body>
</html>
