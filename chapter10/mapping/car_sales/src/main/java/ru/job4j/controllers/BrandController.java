package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.CarDetails;
import ru.job4j.repository.BrandRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This controllers gives to the client brand car.
 *
 * @author Alexey Voronin.
 * @since 07.11.2017.
 */
public class BrandController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        List<CarDetails> brands = new BrandRepository().getBrands();
        writer.append(mapper.writeValueAsString(brands));
        writer.flush();
    }
}
