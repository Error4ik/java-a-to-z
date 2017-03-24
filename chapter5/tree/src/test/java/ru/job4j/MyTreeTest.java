package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * My tree test.
 *
 * @author Alexey Voronin.
 * @since 24.03.2017.
 */
public class MyTreeTest {

    /**
     * Method add.
     * When add node to tree, should the tree increases.
     */
    @Test
    public void whenAddNodeToTreeShouldTheTreeIncreases() {
        final MyTree<Integer> myTree = new MyTree<>();
        final Node<Integer> root = new Node<>(1);
        final Node<Integer> child1 = new Node<>(2);
        final Node<Integer> child2 = new Node<>(3);
        final int expectedValue = 2;

        myTree.addChild(root, child1);
        myTree.addChild(root, child2);

        assertThat(myTree.getChild(root).size(), is(expectedValue));
    }
}