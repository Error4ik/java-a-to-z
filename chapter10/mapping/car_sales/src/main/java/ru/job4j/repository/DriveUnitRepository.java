package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityByName;
import ru.job4j.models.CarDetails;

import java.util.List;

/**
 * Drive unit repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class DriveUnitRepository extends CommonRepository<CarDetails> {

    /**
     * Return unit by name.
     *
     * @param name name.
     * @return drive unit.
     */
    public CarDetails getDriveUnitByName(@NonNull final String name) {
        return super.getEntityByName(new EntityByName<CarDetails>() {
            @Override
            public CarDetails getEntityByName(Session session) {
                return (CarDetails) session.createQuery("from DriveUnit where name=:name")
                        .setParameter("name", name).uniqueResult();
            }
        });
    }

    /**
     * Return all unit.
     *
     * @return units.
     */
    public List<CarDetails> getAllDriveUnit() {
        return super.getAllEntity(new AllEntity<CarDetails>() {
            @Override
            public List<CarDetails> getAll(Session session) {
                return session.createQuery("from DriveUnit order by name asc").list();
            }
        });
    }

    /**
     * Save drive unit to database.
     *
     * @param driveUnit drive unit.
     * @return id.
     */
    public int save(@NonNull final CarDetails driveUnit) {
        final int[] driveUnitId = {0};
        super.execute(new CRUDOperation<CarDetails>() {
            @Override
            public void execute(Session session, CarDetails value) {
                driveUnitId[0] = (int) session.save(driveUnit);
            }
        }, driveUnit);
        return driveUnitId[0];
    }
}
