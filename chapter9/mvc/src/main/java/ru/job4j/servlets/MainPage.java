package ru.job4j.servlets;

import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main Page servlet.
 *
 * @author Alexey Voronin.
 * @since 12.07.2017.
 */
public class MainPage extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userDao.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/MainPage.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
