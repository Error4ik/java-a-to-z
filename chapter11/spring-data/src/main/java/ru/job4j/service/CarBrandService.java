package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.models.CarDetails;
import ru.job4j.storage.CarBrandStorage;

import java.util.List;

/**
 * Car brand service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class CarBrandService {

    /**
     * The car brands storage.
     */
    @Autowired
    private CarBrandStorage brandStorage;

    /**
     * Get all car brands.
     *
     * @return the list of brands.
     */
    public List<CarDetails> getAll() {
        return this.brandStorage.getAll();
    }

    /**
     * Get car brand by name.
     *
     * @param name name.
     * @return car brand.
     */
    public CarDetails getByName(final String name) {
        return this.brandStorage.getByName(name);
    }
}
