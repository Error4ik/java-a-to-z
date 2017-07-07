package ru.job4j;

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
 * User servlet.
 *
 * @author Alexey Voronin.
 * @since 06.07.2017.
 */
public class UserServlet extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        final int id = Integer.parseInt(req.getParameter("id"));
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(user.getUserByID(id).toString());
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.user.addUser(this.getUser(req));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        this.user.updateUser(id, this.getUser(req));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        this.user.deleteUserByID(id);
    }

    /**
     * Get User from.
     *
     * @param req request.
     * @return user.
     */
    private User getUser(final HttpServletRequest req) {
        User user;
        final String name = req.getParameter("name");
        final String login = req.getParameter("login");
        final String email = req.getParameter("email");
        user = new User(name, login, email, new Date());
        return user;
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Settings settings = new Settings();
        this.user = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
