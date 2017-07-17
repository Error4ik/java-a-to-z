<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin user update</title>
</head>
<body>
<h2>Admin user update.</h2>
<form action="${pageContext.servletContext.contextPath}/admin_update" method="post">
    <c:set var="user" value="${user}"></c:set>
    <input type='hidden' name="id" value="${user.id}">
    <label>Login</label><br/>
    <input type="text" name="login" value="${user.login}"><br/>
    <label>Password</label><br/>
    <input type="password" name="password" value="${user.password}"><br/>
    <label>Email</label><br/>
    <input type="text" name="email" value="${user.email}"><br/>
    <label>Role</label><br/>
    <select name="role">
        <c:forEach items="${roles}" var="role">
            <option value="${role.id}" selected>${role.role}</option>
        </c:forEach>
    </select>
    <br/>
    <br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
