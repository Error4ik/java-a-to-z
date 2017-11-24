package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.CarDetails;
import ru.job4j.models.Transmission;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Transmission repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class TransmissionRepositoryTest {

    private final TransmissionRepository repository = new TransmissionRepository();

    @Test
    public void getTransmissionByName() {
        final CarDetails transmission = new Transmission();
        transmission.setName("manual");
        repository.save(transmission);
        assertThat(transmission.getName(), is(repository.getTransmissionByName(transmission.getName()).getName()));
    }

    @Test
    public void getTransmissions() {
        final CarDetails transmission = new Transmission();
        transmission.setName("automatic");
        repository.save(transmission);
        assertTrue(repository.getTransmissions().contains(transmission));
    }
}