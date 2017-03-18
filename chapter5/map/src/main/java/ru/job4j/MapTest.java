package ru.job4j;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Map Test.
 *
 * @author Alexey Voronin.
 * @since 18.03.2017.
 */
public class MapTest {

    /**
     * String separator.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Class User.
     * Without hashCode and equals.
     */
    public void showMap() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2010, 10, 10);
        final User one = new User("Garry", 2, calendar);
        final User two = new User("Garry", 2, calendar);

        final Map<User, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.printf("Not override:| %s%s", map, separator);
    }

    /**
     * Class UserHash.
     * HashCode.
     */
    public void showMapHashCode() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 5, 2);
        final User one = new UserHash("Garry", 3, calendar);
        final User two = new UserHash("Garry", 3, calendar);

        final Map<User, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.printf("Override hashCode:| %s%s", map, separator);
    }

    /**
     * Class UserEquals.
     */
    public void showMapEquals() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2005, 11, 5);
        final User one = new UserEquals("Garry", 2, calendar);
        final User two = new UserEquals("Garry", 2, calendar);

        final Map<User, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.printf("Override equals:| %s%s", map, separator);
    }

    /**
     * HashCode and equals.
     */
    public void showMapHashAndEquals() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2003, 6, 9);
        final User one = new UserHashAndEquals("Garry", 2, calendar);
        final User two = new UserHashAndEquals("Garry", 2, calendar);

        final Map<User, Object> map = new HashMap<>();
        map.put(one, "one");
        map.put(two, "two");

        System.out.printf("Override equals:| %s%s", map, separator);
    }

    /**
     * Main method.
     * @param args arg.
     */
    public static void main(String[] args) {
        final MapTest mapTest = new MapTest();
        mapTest.showMap();
        mapTest.showMapHashCode();
        mapTest.showMapEquals();
        mapTest.showMapHashAndEquals();
    }
}
