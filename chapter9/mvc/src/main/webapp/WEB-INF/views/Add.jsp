<%--
  Created by IntelliJ IDEA.
  User: Alexey.
  Date: 11.07.2017
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user to database</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/Add" method="post">
    <label>Name </label><input type="text" name="name"><br/>
    <label>Login </label><input type="text" name="login"><br/>
    <label>Email </label><input type="text" name="email"><br/>
    <br/>
    <input type="submit" value="Add user"/>
</form>
</body>
</html>
