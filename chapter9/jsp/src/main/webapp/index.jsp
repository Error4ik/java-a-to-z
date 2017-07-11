<%@ page import="ru.job4j.dao.UserDao" %>
<%@ page import="ru.job4j.dao.UserToDB" %>
<%@ page import="ru.job4j.settings.Settings" %>
<%@ page import="ru.job4j.database.PoolDataSource" %>
<%@ page import="ru.job4j.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Alexey.
  Date: 11.07.2017
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/add.jsp" method="get">
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
    <% Settings settings = new Settings();
        UserDao userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
        for (User user : userDao.getAllUsers()) {
            int id = user.getId();
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getName()%>
        </td>
        <td><%=user.getLogin()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getCreateDate()%>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/update.jsp" method=post>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Update"/>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/delete" method=post>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="Delete"/>
            </form>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
