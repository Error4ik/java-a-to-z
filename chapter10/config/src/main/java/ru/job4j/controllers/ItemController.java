package ru.job4j.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.Item;
import ru.job4j.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Item controller.
 *
 * @author Alexey Voronin.
 * @since 25.10.2017.
 */
public class ItemController extends HttpServlet {

    /**
     * Item service.
     */
    private ItemService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        List<Item> items = service.getItems();
        writer.append(mapper.writeValueAsString(items));
        writer.flush();
    }

    @Override
    public void init() throws ServletException {
        this.service = new ItemService();
    }
}
