package ru.job4j.controllers;

import ru.job4j.dao.RoleDao;
import ru.job4j.dao.RoleToDB;
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
import java.io.IOException;
import java.util.Date;

/**
 * Admin add new user.
 *
 * @author Alexey Voronin.
 * @since 16.07.2017.
 */
public class AdminAddUser extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    /**
     * RoleDao.
     */
    private RoleDao roleDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", roleDao.getAllRole());
        req.getRequestDispatcher("/WEB-INF/views/AdminAddUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String email = req.getParameter("email");
        final Role role = roleDao.getRoleById(Integer.parseInt(req.getParameter("role")));
        this.userDao.addUser(new User(0, login, password, email, new Date(), role));
        resp.sendRedirect(String.format("%s/admin", req.getContextPath()));
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        final String url = settings.getValue("url");
        final String name = settings.getValue("name");
        final String password = settings.getValue("password");
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(url, name, password));
        this.roleDao = new RoleToDB(PoolDataSource.setupDataSource(url, name, password));
    }
}
