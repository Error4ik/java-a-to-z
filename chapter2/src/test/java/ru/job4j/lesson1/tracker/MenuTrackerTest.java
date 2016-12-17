package ru.job4j.lesson1.tracker;


import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

import ru.job4j.lesson1.input.ConsoleInputData;
import ru.job4j.lesson1.input.Input;

import static org.junit.Assert.assertThat;

/**
 * Тест класса MenuTracker.
 */
public class MenuTrackerTest {

    /**
     *
     */
    private final Tracker tracker = new Tracker();

    /**
     *
     */
    private final Input input = new ConsoleInputData();

    /**
     *
     */
    private final MenuTracker menuTracker = new MenuTracker(tracker, input);


    /**
     *
     */
    @Before
    public void init() {
        menuTracker.fillAction();
    }

    /**
     * Тест проверят что метод fillAction заполняет массив действий.
     */
    @Test
    public void fillAction() {
        final int expectedCount = 9;
        assertThat(menuTracker.getCountAction(), is(expectedCount));
    }

    /**
     * Тест проверяет что метод getMenuNumbers возвращает массив допустимых пунктов меню.
     */
    @Test
    public void getMenuNumbers() {
        final int[] expectedNumbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(menuTracker.getMenuNumbers(), is(expectedNumbers));
    }

    /**
     *
     */
    @Test
    public void addAction() {

    }

    /**
     *
     */
    @Test
    public void showMenu() {

    }

    /**
     *
     */
    @Test
    public void select() {

    }

    /**
     *
     */
    @Test
    public void getCountAction() {
        final int expectedCount = 9;
        assertThat(menuTracker.getCountAction(), is(expectedCount));
    }
}