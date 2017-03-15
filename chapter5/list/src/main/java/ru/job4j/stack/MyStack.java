package ru.job4j.stack;

import ru.job4j.linked_list.MyLinkedList;

/**
 * My Stack.
 *
 * @param <T>
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class MyStack<T> {

    /**
     * Container by contains items to my stack.
     */
    private final MyLinkedList<T> list;

    /**
     * Constructor.
     * Init the container.
     */
    public MyStack() {
        this.list = new MyLinkedList<T>();
    }

    /**
     * Add item to my stack.
     *
     * @param t item to added.
     */
    public void push(final T t) {
        this.list.add(t);
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     *
     * @return item.
     */
    public T pop() {
        T value = this.list.get(this.list.getSize() - 1);
        this.list.remove(this.list.getSize() - 1);
        return value;
    }
}
