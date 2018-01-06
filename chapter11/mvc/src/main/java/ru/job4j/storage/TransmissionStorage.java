package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Transmission storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class TransmissionStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all transmissions from database.
     *
     * @return the list of transmissions.
     */
    public List<CarDetails> getAll() {
        return (List<CarDetails>) this.template.find("from Transmission");
    }

    /**
     * Get transmission by name.
     *
     * @param name name.
     * @return transmission.
     */
    public CarDetails getByName(final String name) {
        return (CarDetails) this.template.find("from Transmission where name=?", name).get(0);
    }
}
