package ru.job4j.remove_duplicate_test;

import ru.job4j.remove_duplicate.RemoveDuplicate;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса удаляющего дубликаты.
 */
public class RemoveDuplicateTest {

    /**
     * Тест для удаления дбликатов в массиве.
     */
    @Test
    public void testRemoveDuplicate() {
        final String[] actual = new String[] {"Игорь", "Вася", "Алексей", "Миша", "Саша", "Игорь", "Алексей", "Саша", "Игорь", "Алексей", "Миша"};
        final String[] expected = new String[] {"Игорь", "Вася", "Алексей", "Миша", "Саша"};
        RemoveDuplicate removeDuplicate = new RemoveDuplicate();
        assertThat(expected, is(removeDuplicate.removeDuplicate(actual)));
    }
}