package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Engine repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class EngineRepository extends CommonRepository<CarDetails> {

    /**
     * Return engine by name.
     *
     * @param name name.
     * @return engine.
     */
    public CarDetails getEngineByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<CarDetails>() {
            @Override
            public CarDetails getEntityByName(Session session) {
                return (CarDetails) session.createQuery("from Engine where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }

    /**
     * Return list engine.
     *
     * @return engines.
     */
    public List<CarDetails> getEngines() {
        return super.getAllEntity(new AllEntity<CarDetails>() {
            @Override
            public List<CarDetails> getAll(Session session) {
                return session.createQuery("from Engine order by name asc").list();
            }
        });
    }
}
