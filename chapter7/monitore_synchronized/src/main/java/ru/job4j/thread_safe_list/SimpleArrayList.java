package ru.job4j.thread_safe_list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Thread safe list.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class SimpleArrayList<T> implements Iterable<T> {

    /**
     * Default capacity.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Index array.
     */
    private int index;

    /**
     * Container to elements.
     */
    private T[] array;

    /**
     * Default constructor.
     */
    public SimpleArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor.
     *
     * @param size size to array.
     */
    public SimpleArrayList(final int size) {
        this.array = (T[]) new Object[size];
    }

    /**
     * Add new item to list.
     *
     * @param item item.
     */
    public synchronized void add(final T item) {
        if (this.array.length == this.index) {
            this.array = Arrays.copyOf(this.array, this.array.length * 2);
        }
        this.array[this.index++] = item;
    }

    /**
     * Get element from position.
     *
     * @param position position.
     * @return element.
     */
    public synchronized T get(final int position) {
        if (position < 0 && position >= this.index) {
            throw new IndexOutOfBoundsException("Index incorrect.");
        }
        return (T) this.array[position];
    }

    /**
     * Get size to array.
     *
     * @return size.
     */
    public synchronized int getSize() {
        return this.array.length;
    }

    /**
     * Get the number of elements in an array.
     *
     * @return amount.
     */
    public synchronized int getAmountOfItems() {
        return this.index;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    /**
     * My class iterator.
     *
     * @param <T> any type.
     */
    private class MyIterator<T> implements Iterator<T> {

        /**
         * Index to array.
         */
        private int index;

        /**
         * Constructor.
         */
        MyIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < SimpleArrayList.this.array.length;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements!");
            }
            return (T) SimpleArrayList.this.array[index++];
        }
    }
}
