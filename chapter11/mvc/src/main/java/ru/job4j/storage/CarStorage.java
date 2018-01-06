package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.Car;

/**
 * Car storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class CarStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Save car to database.
     *
     * @param value car.
     * @return saved car.
     */
    @Transactional(readOnly = false)
    public Car save(final Car value) {
        this.template.saveOrUpdate(value);
        return value;
    }
}
