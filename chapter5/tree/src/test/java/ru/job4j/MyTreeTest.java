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

    /**
     * Method find.
     * If node is contained in a tree then its value must return.
     */
    @Test
    public void whenANodeIsContainedInATreeThenItsValueMustReturn() {
        final MyTree<String> tree = new MyTree<>();
        final Node<String> root = new Node<>("root");
        final Node<String> child1 = new Node<>("child1");
        final Node<String> child2 = new Node<>("child2");
        final Node<String> child3 = new Node<>("child3");
        final Node<String> child4 = new Node<>("child4");
        final String expectedValue = "child4";

        tree.addChild(root, child1);
        tree.addChild(root, child2);
        tree.addChild(child1, child3);
        tree.addChild(child1, child4);

        assertThat(tree.find(root, child4), is(expectedValue));
    }
}