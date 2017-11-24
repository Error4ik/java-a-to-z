package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.CarBrand;
import ru.job4j.models.CarDetails;
import ru.job4j.models.CarModel;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Model repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class ModelRepositoryTest {

    private final ModelRepository modelRepository = new ModelRepository();

    private final BrandRepository brandRepository = new BrandRepository();

    @Test
    public void getModelBYName() {
        final CarDetails model = new CarModel();
        model.setName("Q5");
        modelRepository.save(model);
        assertThat(model.getName(), is(modelRepository.getModelByName(model.getName()).getName()));
    }

    @Test
    public void getModelByBrandId() {
        final CarModel model = new CarModel();
        final CarBrand brand = new CarBrand();
        CarDetails carBrand = new CarBrand();
        carBrand.setName("BMW");
        brandRepository.save(carBrand);
        carBrand = brandRepository.getBrandByName(carBrand.getName());
        brand.setId(carBrand.getId());
        brand.setName(carBrand.getName());
        model.setName("M5");
        model.setCarBrand(brand);
        modelRepository.save(model);
        assertTrue(modelRepository.getModelsByBrandId(brand.getId()).contains(model));
    }

}