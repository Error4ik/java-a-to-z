package ru.job4j;

import org.junit.Test;


import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test class.
 *
 * @author Alexey Voronin.
 * @since 06.03.2017.
 */
public class FrogWayTest {

    /**
     * Method getWay. Return short way from start to finish point.
     */
    @Test
    public void whenValidInputValueThenReturnShortWay() {
        final Point start = new Point(11, 7);
        final Point finish = new Point(12, 8);
        final Point[] obstacle = new Point[]{new Point(13, 8), new Point(5, 7)};
        final String expectedValue = String.format("Minimum move: %s%s%s",
                6, System.getProperty("line.separator"), "(14,7)(0,8)(3,8)(6,8)(9,8)(12,8)");

        final FrogWay frogWay = new FrogWay(start, finish, obstacle);


        assertThat(frogWay.getWay(), is(expectedValue));
    }

}