package ru.job4j;

import java.util.LinkedList;
import java.util.List;

/**
 * My Node.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 22.03.2017.
 */
public class Node<T> {

    /**
     * Value this node.
     */
    private T value;

    /**
     * First child.
     */
    private Node<T> parent;

    /**
     * List of descendants.
     */
    private List<Node<T>> childList;

    /**
     * Constructor.
     *
     * @param value any type value.
     */
    public Node(final T value) {
        this.value = value;
        this.childList = new LinkedList<>();
    }

    /**
     * Add child to parent.
     *
     * @param parent parent.
     * @param child  child.
     */
    public void addChild(final Node<T> parent, final Node<T> child) {
        parent.addChild(child);
        child.parent = parent;
    }

    /**
     * Method add child to list.
     *
     * @param newChild child.
     */
    public void addChild(final Node<T> newChild) {
        this.childList.add(newChild);
    }

    /**
     * If there is no parent, then this is the root.
     *
     * @return true if root.
     */
    public boolean isRoot() {
        return this.parent == null;
    }

    /**
     * Get.
     *
     * @return the list of children.
     */
    public List<Node<T>> getChildList() {
        return this.childList;
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
     * If you do not have children, and this is not the root node, then this is a leaf.
     *
     * @return true if leaf.
     */
    public boolean isLeaf() {
        return this.childList.size() == 0 && !this.isRoot();
    }
}
