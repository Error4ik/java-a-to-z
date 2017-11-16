package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.City;

import java.util.List;

/**
 * Ciry repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class CityRepository extends CommonRepository<City> {

    /**
     * Return city by name.
     *
     * @param name name.
     * @return city.
     */
    public City getCityByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<City>() {
            @Override
            public City getEntityByName(Session session) {
                return (City) session.createQuery("from City where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }

    /**
     * Return list by city.
     *
     * @return cities.
     */
    public List<City> getCities() {
        return super.getAllEntity(new AllEntity<City>() {
            @Override
            public List<City> getAll(Session session) {
                return session.createQuery("from City order by name asc").list();
            }
        });
    }
}
