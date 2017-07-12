<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/Add" method="get">
    <input type="submit" value="Add new user"/>
</form>
<table border="3">
    <tr>
        <th>ID</th>
        <th>name</th>
        <th>login</th>
        <th>email</th>
        <th>create date</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/Update" method=get>
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
</body>
</html>
