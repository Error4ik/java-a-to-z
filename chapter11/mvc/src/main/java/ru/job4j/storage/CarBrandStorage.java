package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Car brand storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class CarBrandStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all car brands from database.
     *
     * @return the list of car brand.
     */
    public List<CarDetails> getAll() {
        return (List<CarDetails>) this.template.find("from CarBrand");
    }

    /**
     * Get car brand by name from database.
     *
     * @param name name.
     * @return car brand.
     */
    public CarDetails getByName(final String name) {
        return (CarDetails) this.template.find("from CarBrand where name=?", name).get(0);
    }
}
