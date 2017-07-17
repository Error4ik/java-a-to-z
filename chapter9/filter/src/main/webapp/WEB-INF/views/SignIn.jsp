<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<h2>Authorization.</h2>
<c:if test="${not empty requestScope.error}">
    <div style="background-color:red">
        <c:out value="${error}" />
    </div>
</c:if>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <label>Login: </label><br/>
    <input type="text" name="login"/><br/>
    <label>Password: </label><br/>
    <input type="password" name="password"/><br/>
    <input type="submit" value="Sign In"/><br/>
</form>
</body>
</html>
