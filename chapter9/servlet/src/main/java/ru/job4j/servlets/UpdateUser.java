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
 * Update user servlet.
 *
 * @author Alexey Voronin.
 * @since 09.07.2017.
 */
public class UpdateUser extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final int id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.getUserByID(id);
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>")
                .append("<meta charset = utf-8>")
                .append("<h1>Update user to database</h1>")
                .append("</head>")
                .append("<body>")
                .append(String.format("<form action = %s/update method = post>", req.getContextPath()))
                .append(String.format("<input type = 'hidden' name = 'id' value = '%s'/>", id))
                .append(String.format("Name: <input type = 'text' name = 'name' value = '%s'/>", user.getName()))
                .append("<br />")
                .append(String.format("Login: <input type = 'text' name = 'login' value = '%s'/>", user.getLogin()))
                .append("<br />")
                .append(String.format("Email: <input type = 'text' name = 'email' value = '%s'/>", user.getEmail()))
                .append("<br />").append("<br />")
                .append("<input type = 'submit' value = 'Update'/>")
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
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        this.userDao.updateUser(id, new User(
                0, req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), new Date()));
        resp.sendRedirect(String.format("%s/main", req.getContextPath()));
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
