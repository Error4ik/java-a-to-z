package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * My Directory test.
 *
 * @author Alexey Voronin.
 * @since 20.03.2017.
 */
public class DirectoryTest {

    /**
     * Method getSize().
     * If you do not specify a size when creating the map, the size will be set by default.
     */
    @Test
    public void whenCreatingTheMapDoNotSetSizeThenReturnDefaultSize() {
        final Directory<String, String> directory = new Directory<>();
        final int expectedValue = 16;

        assertThat(directory.getSize(), is(expectedValue));
    }

    /**
     * Method getSize().
     * If you specify a size when creating map, the size will be set.
     */
    @Test
    public void whenIfSpecifyASizeWhenCreatingMapThenSizeWillBeSet() {
        final Directory<String, String> directory = new Directory<>(60);
        final int expectedValue = 60;

        assertThat(directory.getSize(), is(expectedValue));
    }

    /**
     * Method changeCapacity().
     * If the map is filled by 75 percent, then the method changeCapacity is called and map increases to double.
     */
    @Test
    public void whenMapIsFilledToSeventyFivePercentThen() {
        final Directory<String, String> directory = new Directory<>(4);
        final int expectedValue = 8;

        directory.put("1", "1");
        directory.put("2", "2");
        directory.put("3", "3");
        directory.put("4", "4");

        assertThat(directory.getSize(), is(expectedValue));
    }

    /**
     * Method put.
     * This method add key and value to the map.
     */
    @Test
    public void whenAValueIsAddedToTheMapThenNumberOfItemIncreases() {
        final Directory<String, String> directory = new Directory<>();
        final int expectedValue = 3;

        directory.put("1", "1");
        directory.put("2", "2");
        directory.put("3", "3");

        assertThat(directory.getAmountItem(), is(expectedValue));
    }

    /**
     * Method get().
     * Returns the value by key.
     */
    @Test
    public void whenGetThenReturnTheValueByKey() {
        final Directory<String, String> directory = new Directory<>();
        final String expectedValue = "Hello";

        directory.put("1", "Hi");
        directory.put("2", "Bye");
        directory.put("3", "Hello");

        assertThat(directory.get("3"), is(expectedValue));
    }

    /**
     * Method put.
     * If you insert an existing key, the value for that key will be replaced.
     */
    @Test
    public void whenIfInsertAnExistingKeyThenTheValueFromThatKeyBeReplaced() {
        final Directory<String, String> directory = new Directory<>();
        final String expectedValue = "hi";

        directory.put("1", "Hello");
        directory.put("2", "Bye");
        directory.put("2", "hi");

        assertThat(directory.get("2"), is(expectedValue));
    }

    /**
     * Method put.
     * If the hash of the keys match.
     */
    @Test
    public void whenTheHashOfTheKeyMatchThenAddToList() {
        final Directory<String, String> directory = new Directory<>(4);
        final String expectedValue = "Bye";

        directory.put("Даша", "Hello");
        directory.put("Маша", "Bye");

        assertThat(directory.get("Маша"), is(expectedValue));
    }

    /**
     * Method get.
     * If there is no key in the map, then the null returns.
     */
    @Test
    public void whenThereIsNoKeyInTheMapThenTheNullReturns() {
        final Directory<String, String> directory = new Directory<>(4);

        directory.put("Дфша", "Hello");

        assertNull(directory.get("Hello"));
    }

    /**
     * Method delete.
     * If the hash of the keys match.
     */
    @Test
    public void whenTheHashOfTheKeyMatchThenRemoveToListLastValueAndReturnThisValue() {
        final Directory<String, String> directory = new Directory<>(4);
        final String expectedValue = "Bye";

        directory.put("Даша", "Hello");
        directory.put("Маша", "Bye");

        assertThat(directory.delete("Маша"), is(expectedValue));
    }

    /**
     * Method delete.
     * If only one value to map.
     */
    @Test
    public void whenMapContainsOnlyOneValueThenDeleteReturnThisValue() {
        final Directory<String, String> directory = new Directory<>(4);
        final String expectedValue = "Bye";

        directory.put("Маша", "Bye");

        assertThat(directory.delete("Маша"), is(expectedValue));
    }

    /**
     * Method delete.
     * If the hash of the keys match.
     */
    @Test
    public void whenTheHashOfTheKeyMatchThenRemoveToListFirstValueAndReturnThisValue() {
        final Directory<String, String> directory = new Directory<>(4);
        final String expectedValue = "Hello";

        directory.put("Даша", "Hello");
        directory.put("Маша", "Bye");

        assertThat(directory.delete("Даша"), is(expectedValue));
    }

    /**
     * Test Iterator.
     */
    @Test
    public void iteratorTest() {
        final Directory<String, Integer> directory = new Directory<>(8);
        final String[] expectedArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};

        directory.put("1", 1);
        directory.put("2", 2);
        directory.put("3", 3);
        directory.put("4", 4);
        directory.put("5", 5);
        directory.put("6", 6);
        directory.put("7", 7);
        directory.put("8", 8);

        final String[] actualArray = new String[8];
        int index = 0;
        for (String s : directory) {
            actualArray[index++] = s;
        }

        //Arrays.sort(actualArray);

        assertThat(actualArray, is(expectedArray));
    }
}