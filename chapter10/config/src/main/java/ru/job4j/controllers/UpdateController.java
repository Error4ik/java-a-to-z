package ru.job4j.controllers;

import lombok.extern.log4j.Log4j;
import ru.job4j.models.Item;
import ru.job4j.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update controller.
 *
 * @author Alexey Voronin.
 * @since 27.10.2017.
 */
@Log4j
public class UpdateController extends HttpServlet {

    /**
     * Item service.
     */
    private ItemService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int itemId = Integer.parseInt(req.getParameter("id"));
        Item item = this.service.getItemById(itemId);
        item.setDone(!item.isDone());
        this.service.saveOrUpdate(item);
    }

    @Override
    public void init() throws ServletException {
        this.service = new ItemService();
    }
}
