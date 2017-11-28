package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityById;
import ru.job4j.models.Advert;

import java.util.List;

/**
 * Advert repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class AdvertRepository extends CommonRepository<Advert> {

    /**
     * Return advert by id.
     *
     * @param id id.
     * @return advert.
     */
    public Advert getById(@NonNull final int id) {
        return super.getEntityById(new EntityById<Advert>() {
            @Override
            public Advert getEntityById(Session session) {
                return (Advert) session.createQuery("from Advert where id=:id")
                        .setParameter("id", id).uniqueResult();
            }
        });
    }

    /**
     * Return all adverts.
     *
     * @return list.
     */
    public List<Advert> getAdverts() {
        return super.getAllEntity(new AllEntity<Advert>() {
            @Override
            public List<Advert> getAll(Session session) {
                return session.createQuery("from Advert order by id asc").list();
            }
        });
    }

    /**
     * Save advert to database.
     *
     * @param advert advert.
     * @return index.
     */
    public int save(@NonNull final Advert advert) {
        return super.execute(new CRUDOperation<Advert>() {
            @Override
            public int execute(Session session, Advert value) {
                return (int) session.save(advert);
            }
        }, advert);
    }

    /**
     * Update advert to db.
     *
     * @param advert advert.
     */
    public void saveOrUpdate(@NonNull final Advert advert) {
        super.execute(new CRUDOperation<Advert>() {
            @Override
            public int execute(Session session, Advert value) {
                session.saveOrUpdate(advert);
                return 0;
            }
        }, advert);
    }
}
