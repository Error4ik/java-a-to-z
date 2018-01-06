package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.models.City;

import java.util.List;

/**
 * City storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class CityStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Get all cities from database.
     *
     * @return the list of a cities.
     */
    public List<City> getAll() {
        return (List<City>) this.template.find("from City");
    }

    /**
     * Get city by name.
     *
     * @param name name.
     * @return city.
     */
    public City getByName(final String name) {
        return (City) this.template.find("from City where name=?", name).get(0);
    }
}
