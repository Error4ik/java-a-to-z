package ru.job4j.TestTask;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест для класса FindSequence.
 */
public class FindSequenceTest {

    /**
     * Возвращает true если последовательность правильная.
     */
    @Test
    public void whenSequenceTrueThenReturnTrue() {
        final FindSequence findSequence = new FindSequence();
        final String sequence = "((((()))))()()()";
        final boolean result = true;
        assertThat(findSequence.find(sequence), is(result));
    }

    /**
     * Возвращает false если последователность не правильная.
     */
    @Test
    public void whenSequenceFalseThenReturnFalse() {
        final FindSequence findSequence = new FindSequence();
        final String sequence = "((((((((((((()))))))(";
        final boolean result = false;
        assertThat(findSequence.find(sequence), is(result));
    }

    /**
     * Возвращает false если последователность не правильная.
     */
    @Test
    public void whenFirstInvalidThenReturnFalse() {
        final FindSequence findSequence = new FindSequence();
        final String sequence = ")(()()((((())))))";
        final boolean result = false;
        assertThat(findSequence.find(sequence), is(result));
    }

}