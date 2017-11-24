package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.City;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * City repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class CityRepositoryTest {

    private final CityRepository repository = new CityRepository();

    @Test
    public void getCityByName() {
        final City city = new City();
        city.setName("Moscow");
        repository.save(city);
        assertThat(city.getName(), is(repository.getCityByName(city.getName()).getName()));
    }

    @Test
    public void getCities() {
        final City city = new City();
        city.setName("Paris");
        repository.save(city);
        assertTrue(repository.getCities().contains(city));
    }
}