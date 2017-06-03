package ru.job4j.update_tracker.input;

/**
 * StubInput that simulates user input.
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
