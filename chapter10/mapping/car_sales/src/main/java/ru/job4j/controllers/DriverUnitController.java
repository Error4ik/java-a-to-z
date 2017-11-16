package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.CarDetails;
import ru.job4j.repository.DriveUnitRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This controllers gives to the client drive unit.
 *
 * @author Alexey Voronin.
 * @since 07.11.2017.
 */
public class DriverUnitController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        List<CarDetails> driveUnits = new DriveUnitRepository().getAllDriveUnit();
        writer.append(mapper.writeValueAsString(driveUnits));
        writer.flush();
    }
}
