package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.models.User;

/**
 * User storage.
 *
 * @author Alexey Voronin.
 * @since 15.12.2017.
 */
@Repository
public class UserStorage {

    /**
     * Hibernate template.
     */
    @Autowired
    private HibernateTemplate template;

    /**
     * Save user to database.
     *
     * @param value user.
     * @return saved user.
     */
    @Transactional(readOnly = false)
    public User save(final User value) {
        this.template.saveOrUpdate(value);
        return value;
    }

    /**
     * Get user by id.
     *
     * @param id id.
     * @return user.
     */
    public User getById(final int id) {
        return this.template.get(User.class, id);
    }

    /**
     * Get user by name.
     *
     * @param name name.
     * @return user.
     */
    public User getByName(final String name) {
        return (User) this.template.find("from User where name=?", name).get(0);
    }
}
