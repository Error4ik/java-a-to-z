package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.CarDetails;
import ru.job4j.models.CarModel;

import java.util.List;

/**
 * Model repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class ModelRepository extends CommonRepository<CarDetails> {

    /**
     * Return all model by brand.
     *
     * @param id brand id.
     * @return models.
     */
    public List<CarDetails> getModelsByBrandId(@NonNull final int id) {
        return super.getAllEntity(new AllEntity<CarDetails>() {
            @Override
            public List<CarDetails> getAll(Session session) {
                return session.createQuery("from CarModel where carBrand.id IN (?1)").setParameter(1, id).list();
            }
        });
    }

    /**
     * Return model by name.
     *
     * @param name name.
     * @return model.
     */
    public CarDetails getModelByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<CarDetails>() {
            @Override
            public CarDetails getEntityByName(Session session) {
                return (CarModel) session.createQuery("from CarModel where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }
}
