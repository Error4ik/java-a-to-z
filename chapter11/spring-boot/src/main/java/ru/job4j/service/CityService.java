package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.City;
import ru.job4j.repository.CityRepository;

import java.util.List;

/**
 * City service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class CityService {

    /**
     * The cities storage.
     */
    @Autowired
    private CityRepository cityRepository;

    /**
     * Get all cities from storage.
     *
     * @return the list of cities.
     */
    public List<City> getAll() {
        return (List<City>) this.cityRepository.findAll();
    }

    /**
     * Get city by name.
     *
     * @param name name.
     * @return city.
     */
    public City getByName(final String name) {
        return this.cityRepository.findByName(name);
    }
}
