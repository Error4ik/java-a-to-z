package ru.job4j;


/**
 * Binary tree.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 24.03.2017.
 */
public class Binary<T extends Comparable> {

    /**
     * Root node.
     */
    private Leaf<T> root = null;

    /**
     * Add a new node to the tree.
     *
     * @param parent the parent of the new node.
     * @param child  new node.
     */
    public void addNode(final Leaf<T> parent, final Leaf<T> child) {
        if (root == null) {
            root = parent;
        }
        if (parent.compareTo(child.getValue()) == 1) {
            if (parent.getLeft() == null) {
                parent.setLeft(child);
            } else {
                this.addNode(parent.getLeft(), child);
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(child);
            } else {
                this.addNode(parent.getRight(), child);
            }
        }
    }

    /**
     * Method find node and will return its value.
     *
     * @param node node to need find.
     * @return a value in the found node or null.
     */
    public T findNode(final Leaf<T> node) {
        T value = null;
        while (value == null && this.root != null) {
            if (this.root.equals(node)) {
                value = root.getValue();
            } else if (this.root.getLeft() != null || this.root.getRight() != null) {
                if (this.root.compareTo(node.getValue()) == 1) {
                    this.root = this.root.getLeft();
                    this.findNode(node);
                } else {
                    this.root = root.getRight();
                    this.findNode(node);
                }
            } else {
                return null;
            }
        }
        return value;
    }
}
