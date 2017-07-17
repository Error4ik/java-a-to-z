<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update user in the database</title>
</head>
<body>
<h2>User update page.</h2>
<form action="${pageContext.servletContext.contextPath}/user_update" method="post">
    <c:set var="user" value="${user}"></c:set>
    <input type='hidden' name="id" value="${user.id}">
    <label>Login</label><br/>
    <input type="text" name="login" value="${user.login}"><br/>
    <label>Password</label><br/>
    <input type="password" name="password" value="${user.password}"><br/>
    <label>Email</label><br/>
    <input type="text" name="email" value="${user.email}"><br/>
    <br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
