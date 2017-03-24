package ru.job4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * My MyTree.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 22.03.2017.
 */
public class MyTree<T> {

    /**
     * Root node.
     */
    private Node<T> root;

    /**
     * Add child.
     *
     * @param parent root node.
     * @param child  child.
     */
    public void addChild(final Node<T> parent, final Node<T> child) {
        if (root == null) {
            root = parent;
        }
        parent.getChild().add(child);
    }


    /**
     * Method return all child value to root.
     *
     * @param root root node.
     * @return list value.
     */
    public List<T> getChild(final Node<T> root) {
        List<T> child = new LinkedList<T>();
        if (root.getChild().size() != 0) {
            for (Node<T> node : root.getChild()) {
                child.add(node.getValue());
            }
        }
        return child;
    }
}
