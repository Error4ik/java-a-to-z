package ru.job4j.array_list;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MySimpleList.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 13.03.2017.
 */
public class MySimpleList<T> implements SimpleList<T>, Iterable<T> {

    /**
     * Default size.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * array.
     */
    private T[] array;

    /**
     * Index to array.
     */
    private int index;

    /**
     * Constructor.
     * If no capacity is specified, the capacity is set by default.
     */
    public MySimpleList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor.
     *
     * @param capacity size to array.
     */
    public MySimpleList(final int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    @Override
    public void add(T t) {
        if (this.array.length == this.index) {
            final Object[] oldArray = this.array;
            this.array = (T[]) new Object[this.array.length * 2];
            System.arraycopy(oldArray, 0, this.array, 0, oldArray.length);
        }
        this.array[index++] = t;
    }

    @Override
    public T get(final int index) {
        if (index >= this.array.length) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.array[index];
    }

    /**
     * Get size.
     *
     * @return size to array.
     */
    public int getSize() {
        return this.array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    /**
     * Class MyIterator.
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
            return MySimpleList.this.array.length > this.index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items!");
            }
            return (T) MySimpleList.this.array[index++];
        }
    }
}
