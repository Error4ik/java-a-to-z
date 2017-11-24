package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Transmission repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class TransmissionRepository extends CommonRepository<CarDetails> {

    /**
     * Get transmission by name.
     *
     * @param name name.
     * @return entity.
     */
    public CarDetails getTransmissionByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<CarDetails>() {
            @Override
            public CarDetails getEntityByName(Session session) {
                return (CarDetails) session.createQuery("from Transmission where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }

    /**
     * Return all transmission.
     *
     * @return entities.
     */
    public List<CarDetails> getTransmissions() {
        return super.getAllEntity(new AllEntity<CarDetails>() {
            @Override
            public List<CarDetails> getAll(Session session) {
                return session.createQuery("from Transmission order by name asc").list();
            }
        });
    }

    /**
     * Save transmission to database.
     *
     * @param transmission transmission.
     * @return id.
     */
    public int save(@NonNull final CarDetails transmission) {
        final int[] transmissionId = {0};
        super.execute(new CRUDOperation<CarDetails>() {
            @Override
            public void execute(Session session, CarDetails value) {
                transmissionId[0] = (int) session.save(transmission);
            }
        }, transmission);
        return transmissionId[0];
    }
}
