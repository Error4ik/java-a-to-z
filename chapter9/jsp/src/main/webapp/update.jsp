<%@ page import="ru.job4j.dao.UserDao" %>
<%@ page import="ru.job4j.dao.UserToDB" %>
<%@ page import="ru.job4j.database.PoolDataSource" %>
<%@ page import="ru.job4j.model.User" %>
<%@ page import="ru.job4j.settings.Settings" %><%--
  Created by IntelliJ IDEA.
  User: Alexey.
  Date: 11.07.2017
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user in the database</title>
</head>
<body>
<% Settings settings = new Settings();
    UserDao userDao = new UserToDB(PoolDataSource.setupDataSource(
            settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    int id = Integer.parseInt(request.getParameter("id"));
    User user = userDao.getUserByID(id);
%>
<form action="<%=request.getContextPath() %>/update" method="post">
    <input type='hidden' name="id" value="<%=id%>">
    <label>Name </label><input type="text" name="name" value="<%=user.getName()%>"><br/>
    <label>Login </label><input type="text" name="login" value="<%=user.getLogin()%>"><br/>
    <label>Email </label><input type="text" name="email" value="<%=user.getEmail()%>"><br/>
    <br/>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
