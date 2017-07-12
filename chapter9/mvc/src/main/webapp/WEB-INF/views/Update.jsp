<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update user in the database</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/Update" method="post">
    <c:set var="user" value="${user}"></c:set>
        <input type='hidden' name="id" value="${user.id}">
        <label>Name </label><input type="text" name="name" value="${user.name}"><br/>
        <label>Login </label><input type="text" name="login" value="${user.login}"><br/>
        <label>Email </label><input type="text" name="email" value="${user.email}"><br/>
        <br/>
        <input type="submit" value="Update"/>
</form>
</body>
</html>
