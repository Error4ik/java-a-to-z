package ru.job4j;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * My Node test.
 *
 * @author Alexey Voronin.
 * @since 24.03.2017.
 */
public class NodeTest {

    /**
     * Method addChild.
     * When adding a child, the child will be added.
     */
    @Test
    public void whenAddingAChildTheChildWillBeAdded() {
        final Node<Integer> root = new Node<>(1);
        final Node<Integer> child = new Node<>(2);
        final int expectedValue = 2;

        root.addChild(root, child);
        Iterator<Node<Integer>> iterator = root.getChildList().iterator();

        assertThat(iterator.next().getValue(), is(expectedValue));
    }

    /**
     * Method isRoot.
     */
    @Test
    public void whenNodeIsRootThenReturnTrue() {
        final Node<Integer> root = new Node<>(1);

        assertTrue(root.isRoot());
    }

    /**
     * Method isLeaf.
     */
    @Test
    public void whenNodeIsLeafThenReturnTrue() {
        final Node<Integer> root = new Node<>(1);
        final Node<Integer> child = new Node<>(2);

        root.addChild(root, child);

        assertTrue(child.isLeaf());
    }
}