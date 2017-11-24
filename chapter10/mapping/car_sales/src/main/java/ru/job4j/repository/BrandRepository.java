package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
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

    /**
     * Save brand to database.
     *
     * @param brand brand.
     * @return index.
     */
    public int save(@NonNull final CarDetails brand) {
        final int[] brandId = {0};
        super.execute(new CRUDOperation<CarDetails>() {
            @Override
            public void execute(Session session, CarDetails value) {
                brandId[0] = (int) session.save(brand);
            }
        }, brand);
        return brandId[0];
    }
}
