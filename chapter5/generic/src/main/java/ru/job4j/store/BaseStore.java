package ru.job4j.store;

import ru.job4j.SimpleArray;

/**
 * Base IStore class.
 *
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public class BaseStore implements IStore {

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

    /**
     * Get array.
     * @return array.
     */
    public SimpleArray<Base> getSimpleArray() {
        return simpleArray;
    }
}
