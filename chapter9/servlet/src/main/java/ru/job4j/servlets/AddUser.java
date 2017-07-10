package ru.job4j.servlets;

import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.User;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Add user servlet.
 *
 * @author Alexey Voronin.
 * @since 08.07.2017.
 */
public class AddUser extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>")
                .append("<meta charset = utf-8>")
                .append("<h1>Add new user to database</h1>")
                .append("</head>")
                .append("<body>")
                .append(String.format("<form action = %s/add method = post>", req.getContextPath()))
                .append("Name: <input type = 'text' name = 'name'/>").append("<br />")
                .append("Login: <input type = 'text' name = 'login'/>").append("<br />")
                .append("Email: <input type = 'text' name = 'email'/>").append("<br />").append("<br/>")
                .append("<input type = 'submit' value = 'Add user'/>")
                .append("</form>")
                .append("</body>")
                .append("</html>");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(sb.toString());
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("utf-8");
        this.userDao.addUser(
                new User(0, req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), new Date()));
        resp.sendRedirect(String.format("%s/main", req.getContextPath()));
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
