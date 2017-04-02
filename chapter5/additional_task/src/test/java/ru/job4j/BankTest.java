package ru.job4j;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test Bank.
 *
 * @author Alexey Voronin.
 * @since 02.04.2017.
 */
public class BankTest {

    /**
     * Method maxPeople.
     */
    @Test
    public void whenCallMaxPeopleMethodShouldReturnTimeIntervalWithTheMaximumNumberOfPeople() {
        final Bank bank = new Bank();
        final Human hum1 = new Human("8:03", "12:20");
        final Human hum2 = new Human("8:12", "11:10");
        final Human hum3 = new Human("8:35", "12:03");
        final Human hum4 = new Human("8:50", "10:09");
        final List<Human> list = new ArrayList<>();
        list.add(hum1);
        list.add(hum2);
        list.add(hum3);
        list.add(hum4);
        final String expectedValue = "The maximum number of people (4) in the bank was from: 8:50 - 10:09";

        final String actualValue = bank.maxPeople(list);

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method maxPeople.
     * With a large number of values.
     */
    @Test
    public void whenCallMaxPeopleWhitALargeNumberOfValueShouldReturnTimeIntervalWithTheMaximumNumberOfPeople() {
        final Bank bank = new Bank();
        final Human hum1 = new Human("8:03", "12:20");
        final Human hum2 = new Human("8:12", "11:10");
        final Human hum3 = new Human("8:35", "12:03");
        final Human hum4 = new Human("8:50", "10:09");
        final Human hum5 = new Human("8:55", "11:23");
        final Human hum6 = new Human("8:57", "12:30");
        final Human hum7 = new Human("9:50", "10:08");
        final Human hum8 = new Human("9:55", "9:59");
        final Human hum9 = new Human("9:57", "9:58");
        final Human hum10 = new Human("11:00", "13:50");
        final List<Human> list = new ArrayList<>();
        list.add(hum1);
        list.add(hum2);
        list.add(hum3);
        list.add(hum4);
        list.add(hum5);
        list.add(hum6);
        list.add(hum7);
        list.add(hum8);
        list.add(hum9);
        list.add(hum10);
        final String expectedValue = "The maximum number of people (9) in the bank was from: 9:57 - 9:58";

        final String actualValue = bank.maxPeople(list);

        assertThat(actualValue, is(expectedValue));
    }
}