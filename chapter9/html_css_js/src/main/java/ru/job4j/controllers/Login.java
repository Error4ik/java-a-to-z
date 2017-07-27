package ru.job4j.controllers;

import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.City;
import ru.job4j.model.Country;
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
 * Login controller..
 *
 * @author Alexey Voronin.
 * @since 26.07.2017.
 */
public class Login extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(String.format("%s/login.html", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = userDao.getUserByLoginAndPassword(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect(String.format("%s/", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credentials is not valid!");
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        }
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
        final User user = this.userDao.getUserByLoginAndPassword("admin", "admin");
        if (user == null) {
            this.userDao.addUser(new User(0, "admin", "admin", "admin@admin.ru", new Date(), new Role(1, "admin"),
                    new Country(1, "Russia"), new City(1, "Moscow", 1)));
        }
    }
}
