package ru.job4j.controllers;

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
import java.util.Date;

/**
 * User update page.
 *
 * @author Alexey Voronin.
 * @since 16.07.2017.
 */
public class UpdateUser extends HttpServlet {

    /**
     * User dao.
     */
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", userDao.getUserByID(Integer.parseInt(req.getParameter("id"))));
        req.getRequestDispatcher("/WEB-INF/views/UserUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        final User user = userDao.getUserByID(id);
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        userDao.updateUser(id, new User(0, login, password, email, new Date(), user.getRole()));
        req.setAttribute("id", id);
        resp.sendRedirect(String.format("%s/user", req.getContextPath()));
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
