package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Engine;
import ru.job4j.repository.EngineRepository;

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
    private EngineRepository engineRepository;

    /**
     * Get all engines from storage.
     *
     * @return the list of engines.
     */
    public List<Engine> getAll() {
        return (List<Engine>) this.engineRepository.findAll();
    }

    /**
     * Get engine by name from storage.
     *
     * @param name name.
     * @return engine.
     */
    public Engine getByName(String name) {
        return this.engineRepository.findByName(name);
    }
}
