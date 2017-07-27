package ru.job4j.controllers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.dao.RoleDao;
import ru.job4j.dao.RoleToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.Role;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Role json.
 *
 * @author Alexey Voronin.
 * @since 26.07.2017.
 */
public class RoleJson extends HttpServlet {

    /**
     * Role json.
     */
    private RoleDao roleDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        List<Role> roles = roleDao.getAllRole();
        writer.append(mapper.writeValueAsString(roles));
        writer.flush();
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.roleDao = new RoleToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
