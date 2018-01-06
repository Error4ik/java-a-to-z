package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.Image;

/**
 * Image storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class ImageStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Save image to database.
     *
     * @param value image.
     * @return saved image.
     */
    @Transactional(readOnly = false)
    public Image save(final Image value) {
        this.template.saveOrUpdate(value);
        return value;
    }

    /**
     * Get image by id.
     *
     * @param id id.
     * @return image.
     */
    public Image getById(final int id) {
        return this.template.get(Image.class, id);
    }
}
