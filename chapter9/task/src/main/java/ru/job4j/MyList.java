package ru.job4j;

/**
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 02.08.2017.
 */
public class MyList<T> {

    /**
     * First node.
     */
    private Node first;

    /**
     * Add value to list.
     *
     * @param value value.
     */
    public void add(final T value) {
        Node node = new Node();
        node.value = value;
        if (this.first != null) {
            Node tmp = this.first;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = node;
        } else {
            this.first = node;
        }
    }

    /**
     * Reverse list.
     */
    public void reverse() {
        Node tmp;
        Node newFirst = null;
        while ((tmp = this.first) != null) {
            this.first = this.first.next;
            tmp.next = newFirst;
            newFirst = tmp;
        }
        this.first = newFirst;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = this.first;
        while (node != null) {
            sb.append(node.value).append(" ");
            node = node.next;
        }
        return sb.toString().trim();
    }


    /**
     * Node.
     */
    private class Node {
        /**
         * Value.
         */
        private T value;

        /**
         * Next node.
         */
        private Node next;
    }

}
