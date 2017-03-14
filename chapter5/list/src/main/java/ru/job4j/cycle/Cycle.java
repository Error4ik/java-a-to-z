package ru.job4j.cycle;

/**
 * Check the linked list for the available cycle.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class Cycle<T> {

    /**
     * Check cycle.
     *
     * @param first start element.
     * @return true if found cycle.
     */
    public boolean hasCycle(final Node<T> first) {
        boolean flag = false;
        Node<T> current = first;
        while ((current = current.getNext()) != null) {
            if (first.equals(current.getNext())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
