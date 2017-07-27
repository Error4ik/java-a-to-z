package ru.job4j.controllers;

import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.dao.RoleDao;
import ru.job4j.dao.RoleToDB;
import ru.job4j.dao.CountryToDB;
import ru.job4j.dao.CountryDao;
import ru.job4j.dao.CityDao;
import ru.job4j.dao.CityToDB;
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
 * Edit user controller.
 *
 * @author Alexey Voronin.
 * @since 26.07.2017.
 */
public class Edit extends HttpServlet {

    /**
     * User dao.
     */
    private UserDao userDao;

    /**
     * Role dao.
     */
    private RoleDao roleDao;

    /**
     * Country dao.
     */
    private CountryDao countryDao;

    /**
     * City dao.
     */
    private CityDao cityDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int id = Integer.parseInt(req.getParameter("id"));
        final User user = userDao.getUserByID(id);
        final HttpSession session = req.getSession();
        session.setAttribute("currentUser", user);
        resp.sendRedirect(String.format("%s/edit.html", req.getContextPath()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String login = req.getParameter("edit-login");
        String pass = req.getParameter("edit-password");
        String email = req.getParameter("edit-email");
        Role role = roleDao.getRoleByName(req.getParameter("edit-role"));
        Country country = countryDao.getCountryByName(req.getParameter("edit-country"));
        City city = cityDao.getCityByName(req.getParameter("edit-city"));
        this.userDao.updateUser(id, new User(0, login, pass, email, new Date(), role, country, city));
        resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        final String url = settings.getValue("url");
        final String name = settings.getValue("name");
        final String pass = settings.getValue("password");
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(url, name, pass));
        this.roleDao = new RoleToDB(PoolDataSource.setupDataSource(url, name, pass));
        this.countryDao = new CountryToDB(PoolDataSource.setupDataSource(url, name, pass));
        this.cityDao = new CityToDB(PoolDataSource.setupDataSource(url, name, pass));
    }
}
