package ru.job4j;

import java.util.Objects;

/**
 * Tree node.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 25.03.2017.
 */
public class Leaf<T extends Comparable> implements Comparable<T> {

    /**
     * Value.
     */
    private final T value;

    /**
     * Left node.
     */
    private Leaf<T> left;

    /**
     * Right node.
     */
    private Leaf<T> right;

    /**
     * Constructor.
     *
     * @param value value.
     */
    public Leaf(final T value) {
        this.value = value;
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
     * @return left node.
     */
    public Leaf<T> getLeft() {
        return left;
    }

    /**
     * Get.
     *
     * @return right node.
     */
    public Leaf<T> getRight() {
        return right;
    }

    /**
     * Set.
     *
     * @param left set new left node.
     */
    public void setLeft(Leaf<T> left) {
        this.left = left;
    }

    /**
     * Set.
     *
     * @param right set new right node.
     */
    public void setRight(Leaf<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(T o) {
        return this.getValue().compareTo(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Leaf)) {
            return false;
        }
        Leaf<?> leaf = (Leaf<?>) o;
        return Objects.equals(getValue(), leaf.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
