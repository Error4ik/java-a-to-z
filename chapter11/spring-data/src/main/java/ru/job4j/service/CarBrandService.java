package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.CarBrand;
import ru.job4j.repository.CarBrandRepository;

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
    private CarBrandRepository brandRepository;

    /**
     * Get all car brands.
     *
     * @return the list of brands.
     */
    public List<CarBrand> getAll() {
        return (List<CarBrand>) this.brandRepository.findAll();
    }

    /**
     * Get car brand by name.
     *
     * @param name name.
     * @return car brand.
     */
    public CarBrand getByName(final String name) {
        return this.brandRepository.findByName(name);
    }
}
