package ru.job4j.controllers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.dao.CountryDao;
import ru.job4j.dao.CountryToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.Country;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Country json.
 *
 * @author Alexey Voronin.
 * @since 26.07.2017.
 */
public class CountryJson extends HttpServlet {

    /**
     * Country dao.
     */
    private CountryDao countryDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter writer = resp.getWriter();
        List<Country> countries = countryDao.getAllCountry();
        writer.append(mapper.writeValueAsString(countries));
        writer.flush();
    }

    @Override
    public void init() throws ServletException {
        Settings settings = new Settings();
        this.countryDao = new CountryToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }
}
