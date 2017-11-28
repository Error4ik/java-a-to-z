package ru.job4j.controllers;

import ru.job4j.models.Advert;
import ru.job4j.repository.AdvertRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Update advert controller.
 *
 * @author Alexey Voronin.
 * @since 13.11.2017.
 */
public class UpdateController extends HttpServlet {

    private AdvertRepository advertRepository = new AdvertRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int advertId = Integer.parseInt(req.getParameter("id"));
        Advert advert = advertRepository.getById(advertId);
        advert.setSale(!advert.getSale());
        advertRepository.saveOrUpdate(advert);
        resp.sendRedirect("index.html");
    }
}
