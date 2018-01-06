package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.CarDetails;
import ru.job4j.storage.CarBodyStorage;

import java.util.List;

/**
 * Car body service.
 *
 * @author Alexey Voronin.
 * @since 19.12.2017.
 */
@Service
public class CarBodyService {

    /**
     * The car bodies storage.
     */
    @Autowired
    private CarBodyStorage carBodyStorage;

    /**
     * Get all car bodies from database.
     *
     * @return the list of car bodies.
     */
    public List<CarDetails> getAll() {
        return carBodyStorage.getAll();
    }

    /**
     * Get car body by name from database.
     *
     * @param name name.
     * @return car body.
     */
    public CarDetails getByName(final String name) {
        return this.carBodyStorage.getByName(name);
    }
}
