package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.CarBody;
import ru.job4j.models.CarDetails;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Body repository test..
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class BodyRepositoryTest {

    private final CarBodyRepository repository = new CarBodyRepository();

    @Test
    public void getCarBodyByName() {
        final CarDetails body = new CarBody();
        body.setName("coupe");
        repository.save(body);
        assertThat(body.getName(), is(repository.getBodyByName(body.getName()).getName()));
    }

    @Test
    public void getCarBodies() {
        final CarDetails body = new CarBody();
        body.setName("sedan");
        repository.save(body);
        assertTrue(repository.getBodies().contains(body));
    }

}