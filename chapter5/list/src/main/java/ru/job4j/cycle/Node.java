package ru.job4j.cycle;

/**
 * Node.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class Node<T> {

    /**
     * Item.
     */
    private T item;

    /**
     * Next item.
     */
    private Node<T> next;

    /**
     * Set next item.
     *
     * @param next item.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Get.
     *
     * @return next item.
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Get.
     * @return item.
     */
    public T getItem() {
        return item;
    }
}
