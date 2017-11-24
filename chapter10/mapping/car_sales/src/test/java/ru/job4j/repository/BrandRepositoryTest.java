package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.CarBrand;
import ru.job4j.models.CarDetails;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Brand repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class BrandRepositoryTest {

    private final BrandRepository repository = new BrandRepository();

    @Test
    public void getBrandByNameTest() {
        final CarDetails volvo = new CarBrand();
        volvo.setName("volvo");
        repository.save(volvo);
        assertThat(volvo.getName(), is(repository.getBrandByName(volvo.getName()).getName()));
    }

    @Test
    public void getBrandsTest() {
        final CarDetails volvo = new CarBrand();
        volvo.setName("volvo");
        repository.save(volvo);
        assertTrue(repository.getBrands().contains(volvo));
    }
}