package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Car body repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class CarBodyRepository extends CommonRepository<CarDetails> {

    /**
     * Return car body by name.
     *
     * @param name name.
     * @return body.
     */
    public CarDetails getBodyByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<CarDetails>() {
            @Override
            public CarDetails getEntityByName(Session session) {
                return (CarDetails) session.createQuery("from CarBody where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }

    /**
     * Return all car bodies.
     *
     * @return bodies.
     */
    public List<CarDetails> getBodies() {
        return super.getAllEntity(new AllEntity<CarDetails>() {
            @Override
            public List<CarDetails> getAll(Session session) {
                return session.createQuery("from CarBody order by name asc").list();
            }
        });
    }

    /**
     * Save body to database.
     *
     * @param body body.
     * @return bodyId.
     */
    public int save(@NonNull final CarDetails body) {
        return super.execute(new CRUDOperation<CarDetails>() {
            @Override
            public int execute(Session session, CarDetails value) {
                return (int) session.save(body);
            }
        }, body);
    }
}
