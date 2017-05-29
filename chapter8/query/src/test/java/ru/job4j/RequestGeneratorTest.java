package ru.job4j;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test simple request.
 *
 * @author Alexey Voronin.
 * @since 28.05.2017.
 */
public class RequestGeneratorTest {

    /**
     * One field and one condition.
     */
    @Test
    public void whenOneFieldAndOneConditionShouldReturnOneFieldAndOneCondition() {
        final RequestGenerator request = new RequestGenerator();
        final Map<String, String> map = new LinkedHashMap<>();
        final String expectedValue = "SELECT car.name FROM car WHERE car.price < 5000$;";

        map.put("car.name", "field");
        map.put("car", "table");
        map.put("car.price < 5000$", "condition");

        final String actualValue = request.createRequest(map);
        System.out.println(actualValue);
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Two field and one condition.
     */
    @Test
    public void whenTwoFieldAndOneConditionShouldReturnTwoFieldAndOneCondition() {
        final RequestGenerator request = new RequestGenerator();
        final Map<String, String> map = new LinkedHashMap<>();
        final String expectedValue = "SELECT car.name, car.price FROM car WHERE car.price < 5000$ OR car.name LIKE 'Mercedes';";

        map.put("car.name", "field");
        map.put("car.price", "field");
        map.put("car", "table");
        map.put("car.price < 5000$", "condition");
        map.put("OR car.name LIKE 'Mercedes'", "condition");

        final String actualValue = request.createRequest(map);
        System.out.println(actualValue);
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Four field and many condition.
     */
    @Test
    public void whenFourFieldAndManyConditionShouldReturnFourFieldAndManyCondition() {
        final RequestGenerator request = new RequestGenerator();
        final Map<String, String> map = new LinkedHashMap<>();
        final String expectedValue = String.format("%s%s",
                "SELECT car.name, car.price, car.engine, car.transmission FROM car WHERE car.price < 5000$ ",
                "AND car.engine = 250 AND car.transmission = 'Automatic' OR car.name LIKE '%MW';");

        map.put("car.name", "field");
        map.put("car.price", "field");
        map.put("car.engine", "field");
        map.put("car.transmission", "field");
        map.put("car", "table");
        map.put("car.price < 5000$", "condition");
        map.put("AND car.engine = 250", "condition");
        map.put("AND car.transmission = 'Automatic'", "condition");
        map.put("OR car.name LIKE '%MW'", "condition");

        final String actualValue = request.createRequest(map);
        System.out.println(actualValue);
        assertThat(actualValue, is(expectedValue));
    }

    /**
     * All fields and without condition.
     */
    @Test
    public void whenTheRequestToOutputAllFieldsShouldAllFieldsFromTheTableAreReturned() {
        final RequestGenerator request = new RequestGenerator();
        final Map<String, String> map = new LinkedHashMap<>();
        final String expectedValue = "SELECT * FROM car;";

        map.put("car", "table");

        final String actualValue = request.createRequest(map);
        System.out.println(actualValue);
        assertThat(actualValue, is(expectedValue));
    }
}
