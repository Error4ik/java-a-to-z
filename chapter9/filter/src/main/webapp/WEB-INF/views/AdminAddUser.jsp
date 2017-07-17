<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin add new user</title>
</head>
<body>
<h2>Admin add new user.</h2>
<form action="${pageContext.servletContext.contextPath}/admin_add_user" method="post">
    <label>Login</label><br/>
    <input type="text" name="login"><br/>
    <label>Password</label><br/>
    <input type="text" name="password"><br/>
    <label>Email</label><br/>
    <input type="text" name="email"><br/>
    <label>Role</label><br/>
    <select name="role">
        <c:forEach items="${roles}" var="role">
            <option value="${role.id}" selected>${role.role}</option>
        </c:forEach>
    </select>
    <br/>
    <br/>
    <input type="submit" value="Add user"/>
</form>
</body>
</html>
