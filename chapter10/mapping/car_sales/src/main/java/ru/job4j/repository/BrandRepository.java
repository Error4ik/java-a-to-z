package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Brand repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class BrandRepository extends CommonRepository<CarDetails> {

    /**
     * Return car brand by name.
     *
     * @param name name.
     * @return brand.
     */
    public CarDetails getBrandByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<CarDetails>() {
            @Override
            public CarDetails getEntityByName(Session session) {
                return (CarDetails) session.createQuery("from CarBrand where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }

    /**
     * Return all car brands.
     *
     * @return brands.
     */
    public List<CarDetails> getBrands() {
        return super.getAllEntity(new AllEntity<CarDetails>() {
            @Override
            public List<CarDetails> getAll(Session session) {
                return session.createQuery("from CarBrand order by name asc").list();
            }
        });
    }
}
