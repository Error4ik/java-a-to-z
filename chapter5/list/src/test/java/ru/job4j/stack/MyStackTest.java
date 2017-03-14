package ru.job4j.stack;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * MyStack test.
 *
 * @author Alexey Voronin.
 * @since 14.03.2017.
 */
public class MyStackTest {

    /**
     * Test method push and pop.
     * push added item to stack, and pop return last item and delete him from stack.
     */
    @Test
    public void whenPushThreeItemsToStackThenPopReturnLastItem() {
        final String inputValue1 = "Hello";
        final String inputValue2 = "Bye";
        final String inputValue3 = "World";
        final MyStack<String> stack = new MyStack<>();
        final String expectedValue = inputValue3;

        stack.push(inputValue1);
        stack.push(inputValue2);
        stack.push(inputValue3);

        assertThat(stack.pop(), is(expectedValue));
    }

}