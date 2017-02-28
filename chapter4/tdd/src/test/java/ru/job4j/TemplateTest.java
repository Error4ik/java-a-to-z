package ru.job4j;

import org.junit.Test;
import ru.job4j.exception.NotFoundKeyException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexey Voronin.
 * @since 23.02.2017.
 */
public class TemplateTest {

    /**
     * SimpleGenerator class.
     * Method addTemplateToTheMap.
     */
    @Test
    public void whenAddedTemplateToTheMapThenReturnTrue() {
        final Template generator = new SimpleGenerator();
        final String inputKey = "name";
        final String inputValue = "Alex";

        generator.addTemplateToTheMap(inputKey, inputValue);

        assertTrue(generator.getMap().containsKey(inputKey));
    }

    /**
     * SimpleGenerator class.
     * Method removeTemplateFromMap.
     */
    @Test
    public void whenRemoveTemplateFromMapThenContainReturnFalse() {
        final Template generator = new SimpleGenerator();
        final String inputOneKey = "name";
        final String inputTwoKey = "date";
        final String inputValueOne = "Alex";
        final String inputValueTwo = "23.02.2017";

        generator.addTemplateToTheMap(inputOneKey, inputValueOne);
        generator.addTemplateToTheMap(inputTwoKey, inputValueTwo);

        generator.removeTemplateFromMap(inputOneKey);

        assertFalse(generator.getMap().containsKey(inputOneKey));
    }

    /**
     * SimpleGenerator class.
     * Method generate.
     *
     * @throws Exception key not found.
     */
    @Test
    public void whenTakePatternThenThenReturnModifiedString() throws Exception {
        final Template template = new SimpleGenerator();
        final String keyOne = "name";
        final String valueOne = "Jon";
        final String keyTwo = "subject";
        final String valueTwo = "you";
        final String inputValue = "Hello ${name}, how are ${subject}?";
        final String expectedValue = "Hello Jon, how are you?";

        template.addTemplateToTheMap(keyOne, valueOne);
        template.addTemplateToTheMap(keyTwo, valueTwo);

        String actualValue = template.generate(inputValue);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * SimpleGenerator class.
     * Method generate.
     *
     * @throws Exception key not found.
     */
    @Test
    public void whenAFewWordsFallIntoAPatternThenReturnModifiedString() throws Exception {
        final Template template = new SimpleGenerator();
        final String inputKey = "sos";
        final String inputValue = "Aaa";
        final String inputString = "${sos}, ${sos}, ${sos}";
        final String expectedValue = "Aaa, Aaa, Aaa";

        template.addTemplateToTheMap(inputKey, inputValue);
        final String actualValue = template.generate(inputString);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * @throws Exception key not found.
     */
    @Test(expected = NotFoundKeyException.class)
    public void whenMapNotContainKeyThenThrowException() throws Exception {
        final Template template = new SimpleGenerator();
        final String inputString = "${sos}, ${sos}, ${sos}";

        template.generate(inputString);
    }
}