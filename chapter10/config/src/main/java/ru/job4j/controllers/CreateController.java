package ru.job4j.controllers;

import ru.job4j.models.Item;
import ru.job4j.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Create controller.
 *
 * @author Alexey Voronin.
 * @since 27.10.2017.
 */
public class CreateController extends HttpServlet {

    /**
     * Item service.
     */
    private ItemService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String description = req.getParameter("data");
        Item item = new Item();
        item.setDescription(description);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        service.saveOrUpdate(item);
    }

    @Override
    public void init() throws ServletException {
        this.service = new ItemService();
    }
}
