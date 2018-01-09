package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Car body storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class CarBodyStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all car body from database.
     *
     * @return the list of a car body.
     */
    public List<CarDetails> getAll() {
        return (List<CarDetails>) this.template.find("from CarBody");
    }

    /**
     * Get car detail by name.
     *
     * @param name name.
     * @return car body.
     */
    public CarDetails getByName(final String name) {
        return (CarDetails) this.template.find("from CarBody where name=?", name).get(0);
    }
}
