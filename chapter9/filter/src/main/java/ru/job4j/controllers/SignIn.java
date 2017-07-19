package ru.job4j.controllers;

import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Sign in servlet.
 *
 * @author Alexey Voronin.
 * @since 15.07.2017.
 */
public class SignIn extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/SignIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        User user = userDao.getUserByLoginAndPassword(login, password);
        HttpSession session = req.getSession();
        if (user != null) {
            if (user.getRole().getRole().equals("admin")) {
                session.setAttribute("user", user);
                resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
            } else if (user.getRole().getRole().equals("user")) {
                session.setAttribute("user", user);
                resp.sendRedirect(String.format("%s/user", req.getContextPath()));
            }
        } else {
            req.setAttribute("error", "Credential invalid!");
            doGet(req, resp);
        }

    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
        final User user = this.userDao.getUserByLoginAndPassword("admin", "admin");
        if (user == null) {
            this.userDao.addUser(new User(0, "admin", "admin", "admin@admin.ru", new Date(), new Role(1, "admin")));
        }
    }
}
