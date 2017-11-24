package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityById;
import ru.job4j.models.Car;

/**
 * Car repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class CarRepository extends CommonRepository<Car> {

    /**
     * Save car to database.
     *
     * @param car car.
     * @return car id.
     */
    public int save(@NonNull final Car car) {
        final int[] photoId = {0};
        super.execute(new CRUDOperation<Car>() {
            @Override
            public void execute(Session session, Car value) {
                photoId[0] = (int) session.save(car);
            }
        }, car);
        return photoId[0];
    }

    /**
     * Get car by id.
     *
     * @param id id.
     * @return car or null.
     */
    public Car getCarById(@NonNull final int id) {
        return super.getEntityById(new EntityById<Car>() {
            @Override
            public Car getEntityById(Session session) {
                return (Car) session.createQuery("from Car where id=:id")
                        .setParameter("id", id).uniqueResult();
            }
        });
    }
}
