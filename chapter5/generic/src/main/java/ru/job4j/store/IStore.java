package ru.job4j.store;

/**
 * Interface IStore.
 *
 * @param <T> any type to extended Base class.
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public interface IStore<T extends Base> {

    /**
     * Add value to array.
     *
     * @param value value.
     */
    void add(T value);

    /**
     * Delete value from array.
     *
     * @param value value to need deleted.
     */
    void delete(T value);

    /**
     * Replace value in array.
     *
     * @param value    value to need replaced.
     * @param newValue new value.
     */
    void update(T value, T newValue);

    /**
     * Get.
     * @param id id.
     * @return item by id.
     */
    T get(String id);
}
