package ru.job4j;

/**
 * Human.
 *
 * @author Alexey Voronin.
 * @since 01.04.2017.
 */
public class Human {

    /**
     * Time to enter the bank.
     */
    private final String timeIn;

    /**
     * Time to leave the bank.
     */
    private final String timeOut;

    /**
     * Constructor.
     *
     * @param timeIn  enter the bank.
     * @param timeOut leave the bank.
     */
    public Human(final String timeIn, final String timeOut) {
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    /**
     * Get.
     *
     * @return timeIn.
     */
    public String getTimeIn() {
        return this.timeIn;
    }

    /**
     * Get.
     *
     * @return timeOut.
     */
    public String getTimeOut() {
        return this.timeOut;
    }
}
