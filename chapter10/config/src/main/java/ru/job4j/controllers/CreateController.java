package ru.job4j.controllers;

import ru.job4j.repository.ItemRepository;
import ru.job4j.models.Item;

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String desc = req.getParameter("data");
        Item item = new Item();
        item.setDescription(desc);
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        new ItemRepository().addItem(item);
    }
}
