package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.Advert;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Advert repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class AdvertRepositoryTest {

    private final AdvertRepository advertRepository = new AdvertRepository();

    @Test
    public void getAdvertById() {
        final Advert advert = new Advert();
        advert.setId(advertRepository.save(advert));
        assertThat(advert.getId(), is(advertRepository.getById(advert.getId()).getId()));
    }

    @Test
    public void getAdverts() {
        final Advert advert = new Advert();
        advert.setId(advertRepository.save(advert));
        assertTrue(advertRepository.getAdverts().contains(advert));
    }
}
