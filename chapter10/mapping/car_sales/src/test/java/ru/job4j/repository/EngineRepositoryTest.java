package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.CarDetails;
import ru.job4j.models.Engine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Engine repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class EngineRepositoryTest {

    private final EngineRepository repository = new EngineRepository();

    @Test
    public void getEngineByName() {
        final CarDetails engine = new Engine();
        engine.setName("diesel");
        repository.save(engine);
        assertThat(engine.getName(), is(repository.getEngineByName(engine.getName()).getName()));
    }

    @Test
    public void getEngines() {
        final CarDetails engine = new Engine();
        engine.setName("Gasoline");
        repository.save(engine);
        assertTrue(repository.getEngines().contains(engine));
    }

}