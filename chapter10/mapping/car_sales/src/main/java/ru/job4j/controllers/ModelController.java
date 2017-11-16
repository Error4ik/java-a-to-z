package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.CarDetails;
import ru.job4j.repository.BrandRepository;
import ru.job4j.repository.ModelRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This controllers gives to the client model car.
 *
 * @author Alexey Voronin.
 * @since 07.11.2017.
 */
public class ModelController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        String name = req.getParameter("brand");
        final CarDetails brand = new BrandRepository().getBrandByName(name);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        List<CarDetails> models = new ModelRepository().getModelsByBrandId(brand.getId());
        writer.append(mapper.writeValueAsString(models));
        writer.flush();
    }
}
