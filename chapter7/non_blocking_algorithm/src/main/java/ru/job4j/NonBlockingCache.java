package ru.job4j;

import ru.job4j.exception.OptimisticException;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * Non blocking cache.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 04.05.2017.
 */
public class NonBlockingCache<T extends Model> {

    /**
     * Hash map.
     */
    private final ConcurrentHashMap<UUID, T> map = new ConcurrentHashMap<>();

    /**
     * Add new value to map.
     *
     * @param value value.
     */
    public void add(final T value) {
        this.map.put(value.getUuid(), value);
    }

    /**
     * Remove value from map.
     *
     * @param value value.
     */
    public void remove(final T value) {
        this.map.remove(value.getUuid());
    }

    /**
     * Update value.
     *
     * @param value value.
     */
    public void update(final T value) {
        this.map.computeIfPresent(value.getUuid(), new BiFunction<UUID, T, T>() {
            @Override
            public T apply(UUID uuid, T t) {
                if (t.getVersion() == value.getVersion()) {
                    t.setName(value.getName());
                    t.setAge(value.getAge());
                    t.updateVersion();
                } else {
                    throw new OptimisticException("Versions do not match!");
                }
                return value;
            }
        });
    }
}
