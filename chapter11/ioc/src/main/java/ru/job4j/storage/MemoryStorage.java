package ru.job4j.storage;

import org.springframework.stereotype.Component;
import ru.job4j.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Memory storage, save to object to hash map.
 *
 * @author Alexey Voronin.
 * @since 04.12.2017.
 */
@Component
public class MemoryStorage implements Storage<User> {

    /**
     * Hash map for save object.
     */
    private final Map<Integer, User> memoryStorage = new HashMap<>();

    @Override
    public User save(final User entity) {
        this.memoryStorage.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public User getById(final int id) {
        return this.memoryStorage.get(id);
    }

    @Override
    public List<User> getAllEntity() {
        return new ArrayList<>(this.memoryStorage.values());
    }

    @Override
    public int updateEntity(User entity) {
        return this.memoryStorage.put(entity.getId(), entity).getId();
    }

    @Override
    public int deleteEntity(User entity) {
        return this.memoryStorage.remove(entity.getId()) != null ? 1 : -1;
    }
}
