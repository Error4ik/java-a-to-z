package ru.job4j.store;

import ru.job4j.SimpleArray;

import java.util.NoSuchElementException;

/**
 * Base IStore class.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public class BaseStore<T extends Base> implements IStore<T> {

    /**
     * Storage array.
     */
    private final SimpleArray<Base> simpleArray;

    /**
     * Constructor.
     *
     * @param size size to storage array.
     */
    public BaseStore(final int size) {
        this.simpleArray = new SimpleArray<Base>(size);
    }

    @Override
    public void add(final Base value) {
        this.simpleArray.add(value);
    }

    @Override
    public void delete(final Base value) {
        if (value != null) {
            for (int i = 0; i < this.simpleArray.getSize(); i++) {
                if (value.getId().equals(this.simpleArray.get(i).getId())) {
                    this.simpleArray.delete(i);
                }
            }
        }
    }

    @Override
    public void update(final Base value, final Base newValue) {
        if (value != null && newValue != null) {
            for (int i = 0; i < this.simpleArray.getSize(); i++) {
                if (value.getId().equals(this.simpleArray.get(i).getId())) {
                    this.simpleArray.update(i, newValue);
                }
            }
        }
    }

    @Override
    public T get(String id) {
        T item = null;
        for (int i = 0; i < this.simpleArray.getSize(); i++) {
            if (this.simpleArray.get(i) != null && this.simpleArray.get(i).getId().equals(id)) {
                item = (T) this.simpleArray.get(i);
                break;
            }
        }
        if (item == null) {
            throw new NoSuchElementException("No item!");
        }
        return item;
    }


    /**
     * Get array.
     *
     * @return array.
     */
    public SimpleArray<Base> getSimpleArray() {
        return simpleArray;
    }
}
