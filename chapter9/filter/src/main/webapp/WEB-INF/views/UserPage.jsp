<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
<h2>User page.</h2>
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
            <c:if test="${id == user.id}">
                <td><c:out value="${user.password}"></c:out></td>
            </c:if>
            <c:if test="${id != user.id}">
                <td><c:out value="*****"></c:out></td>
            </c:if>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${date.format(user.createDate)}"></c:out></td>
            <td><c:out value="${user.role.role}"></c:out></td>
            <c:if test="${id == user.id}">
                <td>
                    <form action="${pageContext.servletContext.contextPath}/user_update" method=get>
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
            </c:if>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="${pageContext.servletContext.contextPath}/log_out" method="post">
    <input type="submit" value="LogOut">
</form>
</body>
</html>
