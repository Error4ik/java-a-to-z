package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.City;
import ru.job4j.repository.CityRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This controllers gives to the client city.
 *
 * @author Alexey Voronin.
 * @since 08.11.2017.
 */
public class CityController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<City> cities = new CityRepository().getCities();
        writer.append(mapper.writeValueAsString(cities));
        writer.flush();
    }
}
