package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.Image;
import ru.job4j.storage.ImageStorage;

/**
 * Image service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class ImageService {

    /**
     * The images storage.
     */
    @Autowired
    private ImageStorage photoStorage;

    /**
     * Save image to storage.
     *
     * @param value image.
     * @return saved image.
     */
    public Image save(final Image value) {
        return this.photoStorage.save(value);
    }

    /**
     * Get image by id from storage.
     *
     * @param id id.
     * @return image.
     */
    public Image getById(final int id) {
        return this.photoStorage.getById(id);
    }
}
