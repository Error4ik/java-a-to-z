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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Delete user controller.
 *
 * @author Alexey Voronin.
 * @since 16.07.2017.
 */
public class DeleteUser extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        this.userDao.deleteUserByID(id);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (id != user.getId()) {
            resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
        } else {
            req.getSession().invalidate();
            resp.sendRedirect(String.format("%s/sigin", req.getContextPath()));
        }
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
