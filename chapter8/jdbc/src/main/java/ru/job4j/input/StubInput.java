package ru.job4j.input;

/**
 * StubInput that simulates user input.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class StubInput implements Input {

    /**
     * An array that simulates user input.
     */
    private final String[] answers;

    /**
     * Position to array.
     */
    private int position = 0;

    /**
     * Constructor.
     *
     * @param array input user array.
     */
    public StubInput(final String[] array) {
        this.answers = array;
    }

    @Override
    public String getInput(String msg) {
        return answers[position++];
    }
}
