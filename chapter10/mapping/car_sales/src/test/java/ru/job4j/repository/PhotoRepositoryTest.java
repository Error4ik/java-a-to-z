package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.Photo;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Photo repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class PhotoRepositoryTest {

    private final PhotoRepository repository = new PhotoRepository();

    @Test
    public void getPhotoById() {
        final Photo photo = new Photo();
        photo.setName("image.jpg");
        photo.setId(repository.save(photo));
        assertThat(photo.getName(), is(repository.getPhotoById(photo.getId()).getName()));
    }
}
