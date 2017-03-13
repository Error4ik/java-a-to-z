package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * MySimpleArrayTest.
 *
 * @author Alexey Voronin.
 * @since 13.03.2017.
 */
public class MySimpleListTest {

    /**
     * Method add.
     * Test add items to list.
     */
    @Test
    public void whenItemAddToListThenListContainsThisItem() {
        final String inputValue = "Hello!";
        final String expectedValue = "Hello!";
        final SimpleList<String> myList = new MySimpleList<>();

        myList.add(inputValue);

        assertThat(myList.get(0), is(expectedValue));
    }

    /**
     * Method add.
     * If add items when list is full, this list should increment capacity.
     */
    @Test
    public void whenAddItemToFullListThenThisListIncrementCapacity() {
        final int inputValue = 10;
        final int expectedValue = 4;
        final MySimpleList<Integer> myList = new MySimpleList<>(2);

        myList.add(inputValue);
        myList.add(inputValue);
        myList.add(inputValue);

        assertThat(myList.getSize(), is(expectedValue));
    }

    /**
     * Method get.
     * If get take invalid index, then throws exception.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetTakeInvalidIndexThenTrowsException() {
        final MySimpleList<Integer> myList = new MySimpleList<>(2);

        myList.get(5);
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenIterationThroughTheSheetThenTheCorrectValuesAreReturned() {
        final MySimpleList<String> myList = new MySimpleList<>(5);
        final String inputValue = "Hi";
        final String[] expectedArray = new String[]{"Hi", "Hi", "Hi", "Hi", "Hi"};

        myList.add(inputValue);
        myList.add(inputValue);
        myList.add(inputValue);
        myList.add(inputValue);
        myList.add(inputValue);

        final String[] actualArray = new String[5];
        int index = 0;
        for (String s : myList) {
            actualArray[index++] = s;
        }

        assertThat(actualArray, is(expectedArray));
    }
}