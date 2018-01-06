package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.CarModel;
import ru.job4j.storage.CarModelStorage;

import java.util.List;

/**
 * Car model service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class CarModelService {

    /**
     * The car models storage.
     */
    @Autowired
    private CarModelStorage modelStorage;

    /**
     * Get all car models.
     *
     * @return the list of models.
     */
    public List<CarModel> getAll() {
        return this.modelStorage.getAll();
    }

    /**
     * Get car model by name.
     *
     * @param name name.
     * @return car model.
     */
    public CarModel getByName(final String name) {
        return this.modelStorage.getByName(name);
    }

    /**
     * Get all the models on the brand id.
     *
     * @param id id.
     * @return the list of car models.
     */
    public List<CarModel> getModelsByBrandId(final int id) {
        return this.modelStorage.getModelsByBrandId(id);
    }
}
