package ru.job4j.input;

/**
 * Stub user input.
 */
public class StubInput implements Input {

    /**
     * Input array.
     */
    private final String[] values;

    /**
     * Position input array.
     */
    private int count;

    /**
     * Stub user input.
     * @param values input values.
     */
    public StubInput(final String[] values) {
        this.values = values;
    }

    /**
     * Get values out array.
     * @return array values.
     */
    @Override
    public String[] getInput() {
        return values[count++].trim().split(" ");
    }
}
