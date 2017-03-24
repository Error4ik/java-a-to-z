package ru.job4j;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Find element.
     * @param root root tree.
     * @param child element.
     * @return value the element.
     */
    public T find(final Node<T> root, final Node<T> child) {
        T value = null;
        if (root.equals(child)) {
            value = root.getValue();
        } else if (root.getChild().size() != 0) {
            for (Node<T> node : root.getChild()) {
                if (value == null) {
                    value = find(node, child);
                }
            }
        }
        return value;
    }

    /**
     * Check if the tree is balanced.
     * @param root root node.
     * @return true if tree is balanced.
     */
    public boolean isBalance(final Node<T> root) {
        boolean flag = true;
        if (root.getChild().size() != 2 && root.getChild().size() != 0) {
            flag = false;
        } else {
            for (Node<T> node : root.getChild()) {
                flag = this.isBalance(node);
            }
        }
        return flag;
    }
}
