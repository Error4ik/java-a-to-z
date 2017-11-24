package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.Car;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Car repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class CarRepositoryTest {

    private final CarRepository repository = new CarRepository();

    @Test
    public void saveAndGetCarById() {
        final Car car = new Car();
        car.setId(repository.save(car));
        assertThat(car.getId(), is(repository.getCarById(car.getId()).getId()));
    }

}