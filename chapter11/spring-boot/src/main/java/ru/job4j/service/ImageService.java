package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Image;
import ru.job4j.repository.ImageRepository;

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
    private ImageRepository imageRepository;

    /**
     * Save image to storage.
     *
     * @param value image.
     * @return saved image.
     */
    public Image save(final Image value) {
        return this.imageRepository.save(value);
    }

    /**
     * Get image by id from storage.
     *
     * @param id id.
     * @return image.
     */
    public Image getById(final int id) {
        return this.imageRepository.findOne(id);
    }
}
