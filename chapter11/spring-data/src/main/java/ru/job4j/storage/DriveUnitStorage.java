package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Drive unit storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class DriveUnitStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all drive unit from database.
     *
     * @return the list of a drive unit.
     */
    public List<CarDetails> getAll() {
        return (List<CarDetails>) this.template.find("from DriveUnit");
    }

    /**
     * Get drive unit by name.
     *
     * @param name name.
     * @return drive unit.
     */
    public CarDetails getByName(final String name) {
        return (CarDetails) this.template.find("from DriveUnit where name=?", name).get(0);
    }
}
