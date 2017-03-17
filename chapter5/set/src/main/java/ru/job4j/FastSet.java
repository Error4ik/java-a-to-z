package ru.job4j;

import ru.job4j.array_list.MySimpleList;

/**
 * FastSet.
 * Quick insertion of unique elements.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 16.03.2017.
 */
public class FastSet<T> {

    /**
     * Simple list container.
     */
    private final MySimpleList<T> list;

    /**
     * Constructor.
     */
    public FastSet() {
        this.list = new MySimpleList<T>();
    }

    /**
     * Get list.
     *
     * @return list.
     */
    public MySimpleList<T> getList() {
        return this.list;
    }

    /**
     * Add item to list.
     *
     * @param t element to need add.
     */
    public void add(final T t) {
        int position = this.checkDuplicate(t);
        if (position != -1) {
            this.list.add(position, t);
        }
    }

    /**
     * Check duplicate to array.
     *
     * @param t element to need add.
     * @return The position for inserting an element into a sheet,
     * if an element already exists in the sheet, it will return - 1.
     */
    private int checkDuplicate(final T t) {
        int start = 0;
        int end = this.getList().getAmountOfItems();
        int middle = 0;
        while (start < end) {
            middle = start + (end - start) / 2;
            if (t.hashCode() > this.list.get(middle).hashCode()) {
                start = middle + 1;
            } else if (t.hashCode() < this.list.get(middle).hashCode()) {
                end = middle;
            } else {
                return -1;
            }
        }
        return middle;
    }
}
