package ru.job4j.view;

/**
 * Output to console.
 */
public class ConsoleOutput implements Output {

    /**
     * Print to console.
     *
     * @param string string.
     */
    @Override
    public void print(String string) {
        System.out.println(string);
    }
}
