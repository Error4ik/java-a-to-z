package ru.job4j.linked_list;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * My Linked list Test.
 *
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class MyLinkedListTest {

    /**
     * Method add.
     * Add first item.
     */
    @Test
    public void whenAddToListFirstItemThenThisItemShouldContainedInList() {
        final String inputValue1 = "Hello";

        final MyLinkedList<String> list = new MyLinkedList<>();
        final String expectedValue = inputValue1;

        list.add(inputValue1);

        assertThat(list.get(0), is(expectedValue));
    }

    /**
     * Method add.
     * Add second item.
     */
    @Test
    public void whenAddToListSecondItemThenThisItemShouldContainedInList() {
        final String inputValue1 = "Hello";
        final String inputValue2 = "Bye";

        final MyLinkedList<String> list = new MyLinkedList<>();
        final String expectedValue = inputValue2;

        list.add(inputValue1);
        list.add(inputValue2);

        assertThat(list.get(1), is(expectedValue));
    }

    /**
     * Method add.
     * Add third item.
     */
    @Test
    public void whenAddToListThirdItemThenThisItemShouldContainedInList() {
        final String inputValue1 = "Hello";
        final String inputValue2 = "Bye";
        final String inputValue3 = "Day";

        final MyLinkedList<String> list = new MyLinkedList<>();
        final String expectedValue = inputValue3;

        list.add(inputValue1);
        list.add(inputValue2);
        list.add(inputValue3);

        assertThat(list.get(2), is(expectedValue));
    }

    /**
     * Method get.
     * If index invalid then return null.
     */
    @Test
    public void whenTheMethodGetTakesAWrongIndexThenTheNullReturns() {
        final String inputValue1 = "Hello";
        final String inputValue2 = "Bye";
        final MyLinkedList<String> list = new MyLinkedList<>();

        list.add(inputValue1);
        list.add(inputValue2);

        assertNull(list.get(10));
    }

    /**
     * Method remove.
     * If index invalid then return null.
     */
    @Test
    public void whenTheMethodRemoveTakesAWrongIndexThenTheNullReturns() {
        final String inputValue1 = "Hello";
        final MyLinkedList<String> list = new MyLinkedList<>();

        list.add(inputValue1);

        assertNull(list.remove(5));
    }

    /**
     * Method remove.
     * Remove first item to list.
     */
    @Test
    public void whenRemovedFirstItemThenThisElementShouldRemoveFromList() {
        final String inputValue1 = "Hello";
        final String inputValue2 = "Bye";
        final MyLinkedList<String> list = new MyLinkedList<>();

        list.add(inputValue1);
        list.add(inputValue2);
        list.remove(0);

        assertNull(list.get(1));
    }

    /**
     * Method remove.
     * Remove second item to list.
     */
    @Test
    public void whenRemoveSecondItemToTheListThenThisItemShouldRemoveFromList() {
        final String inputValue1 = "Hello";
        final String inputValue2 = "Bye";
        final MyLinkedList<String> list = new MyLinkedList<>();

        list.add(inputValue1);
        list.add(inputValue2);
        list.remove(1);

        assertNull(list.get(1));
    }

    /**
     * Method remove.
     * Remove the only item in the list then should size list equals zero
     */
    @Test
    public void whenRemoveTheOnlyItemInTheListThenShouldSizeListEqualZero() {
        final String inputValue = "Hello";
        final MyLinkedList<String> list = new MyLinkedList<>();
        final int expectedValue = 0;

        list.add(inputValue);
        list.remove(0);

        assertThat(list.getSize(), is(expectedValue));
    }

    /**
     * Method remove.
     * Add several items and delete any item except the first and last.
     */
    @Test
    public void whenAddSerialItemsAndDeleteAnyItemsExceptTheFirstAndLast() {
        final MyLinkedList<Integer> list = new MyLinkedList<>();
        final int expectedValue = 4;

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.remove(2);

        assertThat(list.get(2), is(expectedValue));
    }
}
