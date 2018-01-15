package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Car;
import ru.job4j.repository.CarRepository;

/**
 * Car service..
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class CarService {

    /**
     * The car storage.
     */
    @Autowired
    private CarRepository carRepository;

    /**
     * Save car to storage.
     *
     * @param value car.
     * @return car.
     */
    public Car save(final Car value) {
        return this.carRepository.save(value);
    }
}
