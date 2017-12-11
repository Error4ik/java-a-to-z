package ru.job4j.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.models.User;

import java.util.List;

/**
 * User storage.
 *
 * @author Alexey Voronin.
 * @since 04.12.2017.
 */
@Component
public class UserStorage implements Storage<User> {

    /**
     * Any storage.
     */
    private final Storage storage;

    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }

    @Override
    public User save(final User entity) {
        return (User) this.storage.save(entity);
    }

    @Override
    public User getById(final int id) {
        return (User) this.storage.getById(id);
    }

    @Override
    public List<User> getAllEntity() {
        return this.storage.getAllEntity();
    }

    @Override
    public int updateEntity(final User entity) {
        return this.storage.updateEntity(entity);
    }

    @Override
    public int deleteEntity(User entity) {
        return this.storage.deleteEntity(entity);
    }
}
