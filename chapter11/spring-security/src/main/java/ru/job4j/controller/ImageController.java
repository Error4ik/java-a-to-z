package ru.job4j.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.service.ImageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Photo controller.
 *
 * @author Alexey Voronin.
 * @since 22.12.2017.
 */
@Controller
public class ImageController {

    /**
     * Photo service.
     */
    @Autowired
    private ImageService photoService;

    /**
     * Mapping web requests /image/{photoID}.
     *
     * @param photoID id photo to be search in the database.
     * @return byte array.
     * @throws IOException if you can not get an array of bytes from the photo.
     */
    @ResponseBody
    @GetMapping(value = "/image/{photoID}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable final int photoID) throws IOException {
        return IOUtils.toByteArray(new FileInputStream(new File(this.photoService.getById(photoID).getImageUrl())));
    }
}
