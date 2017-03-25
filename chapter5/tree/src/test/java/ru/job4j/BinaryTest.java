package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Binary tree test.
 *
 * @author Alexey Voronin.
 * @since 25.03.2017.
 */
public class BinaryTest {

    /**
     * Method addNode.
     * When add new node to tree the node must be contained in the tree in the left part.
     */
    @Test
    public void whenAddNewNodeToTreeTheNodeMustBeContainedInTheTreeInTheLeftPart() {
        final Binary<Integer> tree = new Binary<>();
        final Leaf<Integer> rootNode = new Leaf<>(50);
        final Leaf<Integer> nodeOne = new Leaf<>(35);
        final Leaf<Integer> nodeTwo = new Leaf<>(64);
        final Leaf<Integer> nodeThree = new Leaf<>(45);
        final Leaf<Integer> nodeFour = new Leaf<>(59);
        final int expectedValue = 64;

        tree.addNode(rootNode, nodeOne);
        tree.addNode(rootNode, nodeTwo);
        tree.addNode(rootNode, nodeThree);
        tree.addNode(rootNode, nodeFour);

        assertThat(tree.findNode(nodeTwo), is(expectedValue));
    }

    /**
     * Method addNode.
     * When add new node to tree the node must be contained in the tree in the right part.
     */
    @Test
    public void whenAddNewNodeToTreeTheNodeMustBeContainedInTheTreeInTheRightPart() {
        final Binary<Integer> tree = new Binary<>();
        final Leaf<Integer> rootNode = new Leaf<>(50);
        final Leaf<Integer> nodeOne = new Leaf<>(35);
        final Leaf<Integer> nodeTwo = new Leaf<>(64);
        final int expectedValue = 35;

        tree.addNode(rootNode, nodeOne);
        tree.addNode(rootNode, nodeTwo);

        assertThat(tree.findNode(nodeOne), is(expectedValue));
    }

    /**
     * Method findNode.
     * If node is not contain in tree, return null.
     */
    @Test
    public void whenTreeIsNotContainNodeThenReturnNull() {
        final Binary<Integer> tree = new Binary<>();
        final Leaf<Integer> rootNode = new Leaf<>(50);
        final Leaf<Integer> nodeOne = new Leaf<>(35);
        final Leaf<Integer> nodeTwo = new Leaf<>(55);
        final Leaf<Integer> nodeFive = new Leaf<>(99);

        tree.addNode(rootNode, nodeOne);
        tree.addNode(rootNode, nodeTwo);

        assertNull(tree.findNode(nodeFive));
    }
}