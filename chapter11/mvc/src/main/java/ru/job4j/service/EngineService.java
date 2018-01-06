package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.CarDetails;
import ru.job4j.storage.EngineStorage;

import java.util.List;

/**
 * Engine service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class EngineService {

    /**
     * The engines storage.
     */
    @Autowired
    private EngineStorage engineStorage;

    /**
     * Get all engines from storage.
     *
     * @return the list of engines.
     */
    public List<CarDetails> getAll() {
        return this.engineStorage.getAll();
    }

    /**
     * Get engine by name from storage.
     *
     * @param name name.
     * @return engine.
     */
    public CarDetails getByName(String name) {
        return this.engineStorage.getByName(name);
    }
}
