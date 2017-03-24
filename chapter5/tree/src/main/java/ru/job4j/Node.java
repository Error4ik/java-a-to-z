package ru.job4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Node to tree.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 24.03.2017.
 */
public class Node<T> {

    /**
     * Value.
     */
    private T value;

    /**
     * List child.
     */
    private List<Node<T>> child;

    /**
     * Constructor.
     *
     * @param value value.
     */
    public Node(final T value) {
        this.value = value;
        this.child = new LinkedList<>();
    }

    /**
     * Get.
     *
     * @return value.
     */
    public T getValue() {
        return value;
    }

    /**
     * Get.
     *
     * @return list child.
     */
    public List<Node<T>> getChild() {
        return child;
    }
}