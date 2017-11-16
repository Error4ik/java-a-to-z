package ru.job4j.controllers;

import ru.job4j.repository.PhotoRepository;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * This controllers gives to the client image.
 *
 * @author Alexey Voronin.
 * @since 10.11.2017.
 */
public class ImageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int imageId = Integer.parseInt(req.getPathInfo().substring(1));
        resp.setContentType("image/jpeg");
        File file = new File(new PhotoRepository().getPhotoById(imageId).getImageUrl());
        BufferedImage image = ImageIO.read(file);
        try (OutputStream out = resp.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
        }
    }
}
