package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityById;
import ru.job4j.models.Photo;

/**
 * Photo repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class PhotoRepository extends CommonRepository<Photo> {

    /**
     * Save photo to database and return id.
     *
     * @param photo photo.
     * @return id.
     */
    public int save(@NonNull final Photo photo) {
        final int[] photoId = {0};
        super.execute(new CRUDOperation<Photo>() {
            @Override
            public void execute(Session session, Photo value) {
                photoId[0] = (int) session.save(photo);
            }
        }, photo);
        return photoId[0];
    }

    /**
     * Return photo by id.
     * @param id id.
     * @return photo.
     */
    public Photo getPhotoById(@NonNull final int id) {
        return super.getEntityById(new EntityById<Photo>() {
            @Override
            public Photo getEntityById(Session session) {
                return (Photo) session.createQuery("from Photo where id=:id")
                        .setParameter("id", id).uniqueResult();
            }
        });
    }
}
