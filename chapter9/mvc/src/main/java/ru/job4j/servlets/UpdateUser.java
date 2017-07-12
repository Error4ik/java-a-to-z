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
        req.setAttribute("user", userDao.getUserByID(Integer.parseInt(req.getParameter("id"))));
        req.getRequestDispatcher("/WEB-INF/views/Update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        this.userDao.updateUser(id, new User(
                0, req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), new Date()));
        resp.sendRedirect(String.format("%s/", req.getContextPath()));
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
