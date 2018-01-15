package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.CarBody;
import ru.job4j.repository.CarBodyRepository;

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
    private CarBodyRepository bodyRepository;

    /**
     * Get all car bodies from database.
     *
     * @return the list of car bodies.
     */
    public List<CarBody> getAll() {
        return (List<CarBody>) this.bodyRepository.findAll();
    }

    /**
     * Get car body by name from database.
     *
     * @param name name.
     * @return car body.
     */
    public CarBody getByName(final String name) {
        return this.bodyRepository.findByName(name);
    }
}
