package ru.job4j.cycle;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Cycle test.
 *
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class CycleTest {

    /**
     * Method hasCycle.
     * return true.
     */
    @Test
    public void whenIsFoundCycleShouldReturnTrue() {
        final Cycle<Integer> cycle = new Cycle<>();
        final Node<Integer> n1 = new Node<>();
        final Node<Integer> n2 = new Node<>();
        final Node<Integer> n3 = new Node<>();
        final Node<Integer> n4 = new Node<>();

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n1);

        assertTrue(cycle.hasCycle(n1));
    }

    /**
     * Method hasCycle.
     * return false.
     */
    @Test
    public void whenIsNotFoundCycleShouldReturnFalse() {
        final Cycle<Integer> cycle = new Cycle<>();
        final Node<Integer> n1 = new Node<>();
        final Node<Integer> n2 = new Node<>();
        final Node<Integer> n3 = new Node<>();
        final Node<Integer> n4 = new Node<>();

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        assertFalse(cycle.hasCycle(n1));
    }
}