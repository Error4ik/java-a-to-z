package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.Advert;

import java.util.List;

/**
 * Advert storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class AdvertStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Save advert to database.
     *
     * @param value advert.
     * @return saved advert.
     */
    @Transactional(readOnly = false)
    public Advert save(final Advert value) {
        this.template.saveOrUpdate(value);
        return value;
    }

    /**
     * Get advert by id from database.
     *
     * @param id id.
     * @return advert.
     */
    public Advert getById(final int id) {
        return this.template.get(Advert.class, id);
    }

    /**
     * Get all adverts from database.
     *
     * @return the list of a adverts.
     */
    public List<Advert> getAll() {
        return (List<Advert>) this.template.find("from Advert order by created desc");
    }
}
