package ru.job4j;

/**
 * Simple array class.
 *
 * @param <T> Any type.
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public class SimpleArray<T> {

    /**
     * Array in objects.
     */
    private Object[] array;

    /**
     * Index to array.
     */
    private int index;

    /**
     * Constructor.
     *
     * @param size size to array.
     */
    public SimpleArray(final int size) {
        this.array = new Object[size];
        this.index = 0;
    }

    /**
     * Add value to array.
     *
     * @param value the value to add to the array.
     */
    public void add(final T value) {
        this.array[index++] = value;
    }

    /**
     * Delete value to array.
     *
     * @param index on this index, you need to delete the value from the array.
     */
    public void delete(final int index) {
        this.array[index] = null;
    }

    /**
     * Update value in array to the index.
     *
     * @param index the index you want to update.
     * @param value new value.
     */
    public void update(final int index, final T value) {
        this.array[index] = value;
    }

    /**
     * Get the value from the array by index.
     *
     * @param index index.
     * @return value.
     */
    public T get(final int index) {
        return (T) this.array[index];
    }
}
