<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
<h2>Admin page.</h2>
<table border="3">
    <tr>
        <th>ID</th>
        <th>login</th>
        <th>password</th>
        <th>email</th>
        <th>create date</th>
        <th>role</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.password}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${date.format(user.createDate)}"></c:out></td>
            <td><c:out value="${user.role.role}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/admin_update" method=get>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Update"/>
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method=post>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Delete"/>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.servletContext.contextPath}/log_out" method="post">
    <label>Log out&nbsp</label><input type="submit" value="LogOut">
</form>
<form action="${pageContext.servletContext.contextPath}/admin_add_user" method="get">
    <label>Add new User&nbsp</label><input type="submit" value="Sign Up"/>
</form>
</body>
</html>
