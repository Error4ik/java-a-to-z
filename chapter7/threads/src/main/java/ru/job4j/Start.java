package ru.job4j;


import org.apache.log4j.Logger;

/**
 * Start thread.
 *
 * @author Alexey Voronin.
 * @since 12.04.2017.
 */
public class Start {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Start.class);

    /**
     * Main start method.
     *
     * @param args args.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {
        final String inputValue = String.format("%s%s", "Computer users take it for granted that ",
                "their systems can do more than one thing at a time.");
        final Thread first = new Thread(new NumberOfSpace(inputValue), "Space");
        final Thread second = new Thread(new NumberOfWord(inputValue), "Word");

        LOGGER.info("Program start!");
        System.out.println("Start program!");
        first.start();
        second.start();


        first.join(1000);
        second.join(1000);

        first.interrupt();
        second.interrupt();

        System.out.println("Stop program!");
        LOGGER.info("Program stop!");
    }
}
