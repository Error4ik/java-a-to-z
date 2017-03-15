package ru.job4j;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * My Set.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 15.03.2017.
 */
public class MySet<T> implements Iterable<T>{

    /**
     * DefaultSIze to container.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Array-based container.
     */
    private Object[] array;

    /**
     * Index to array.
     */
    private int index = 0;

    /**
     * Constructor.
     * If not set size to array, used to default value.
     */
    public MySet() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor.
     *
     * @param size size to array.
     */
    public MySet(final int size) {
        this.array = new Object[size];
    }

    /**
     * @param t any type value.
     * @return true if item added to array, and false if value contained to array.
     */
    public boolean add(T t) {
        if (this.array.length <= index) {
            changeCapacity();
        }
        boolean flag = true;
        for (Object item : this.array) {
            if (item != null && item.equals(t)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            this.array[this.index++] = t;
        }
        return flag;
    }

    /**
     * Change the capacity of an array.
     */
    private void changeCapacity() {
        final Object[] oldArray = this.array;
        this.array = new Object[this.array.length * 2];
        System.arraycopy(oldArray, 0, this.array, 0, oldArray.length);
    }

    /**
     * Adds all unique elements from the input array to the current Set.
     * @param t input array.
     */
    public void addAll(final T[] t) {
        for (T item : t) {
            this.add(item);
        }
    }

    /**
     * Get.
     *
     * @return size to array.
     */
    public int size() {
        return this.array.length;
    }

    /**
     * MyIterator.
     *
     * @return iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    /**
     * MyIterator class.
     *
     * @param <T> any type.
     */
    private class MyIterator<T> implements Iterator<T> {

        /**
         * Index to array.
         */
        private int index = 0;

        @Override
        public boolean hasNext() {
            return MySet.this.array.length > index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items!");
            }
            return (T) MySet.this.array[index++];
        }
    }
}
