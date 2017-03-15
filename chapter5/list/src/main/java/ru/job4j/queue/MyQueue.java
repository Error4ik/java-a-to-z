package ru.job4j.queue;

import ru.job4j.linked_list.MyLinkedList;

/**
 * My Queue.
 *
 * @param <T>
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class MyQueue<T> {

    /**
     * Container by contains items to my queue.
     */
    private final MyLinkedList<T> list;

    /**
     * Constructor.
     * Init the container.
     */
    public MyQueue() {
        this.list = new MyLinkedList<T>();
    }

    /**
     * Add item to my queue.
     *
     * @param t item to added.
     */
    public void add(final T t) {
        this.list.add(t);
    }

    /**
     * Retrieves and removes the head of this queue.
     *
     * @return the head of this queue, or null if this queue is empty.
     */
    public T pull() {
        T value = this.list.get(0);
        this.list.remove(0);
        return value;
    }
}
