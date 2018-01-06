package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Engine storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class EngineStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all engines from database.
     *
     * @return the list of engines.
     */
    public List<CarDetails> getAll() {
        return (List<CarDetails>) this.template.find("from Engine");
    }

    /**
     * Get engine by name.
     *
     * @param name name.
     * @return engine.
     */
    public CarDetails getByName(final String name) {
        return (CarDetails) this.template.find("from Engine where name=?", name).get(0);
    }
}
