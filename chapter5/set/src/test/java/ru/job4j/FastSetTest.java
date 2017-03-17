package ru.job4j;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Random;


/**
 * FastSet test.
 *
 * @author Alexey Voronin.
 * @since 17.03.2017.
 */
public class FastSetTest {

    /**
     * Method add.
     */
    @Test(timeout = 3000)
    public void whenAddElementToListShouldAddedOnlyUniqueItems() {
        final FastSet<Integer> set = new FastSet<>();
        final int numberOfIterations = 200_000;
        final Random random = new Random();

        final long start = System.currentTimeMillis();
        for (int i = 0; i < numberOfIterations; i++) {
            set.add(random.nextInt());
        }
        final long end = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("ss");
        System.out.println(format.format(end - start));
    }

}