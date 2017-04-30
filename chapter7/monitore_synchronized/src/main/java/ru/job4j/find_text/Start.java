package ru.job4j.find_text;

import org.apache.log4j.Logger;
import ru.job4j.find_text.exception.InvalidKeyException;

import java.nio.file.Path;
import java.text.SimpleDateFormat;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/**
 * Start class.
 *
 * @author Alexey Voronin.
 * @since 27.04.2017.
 */
public class Start {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Start.class);

    /**
     * Blocking queue.
     */
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    /**
     * The root path from which the search begins.
     */
    private final Path path;

    /**
     * Text to find.
     */
    private final String findString;

    /**
     * Pattern for searching files.
     */
    private final Pattern pattern;

    /**
     * The number of processors in the system.
     */
    private final int threadCount = Runtime.getRuntime().availableProcessors() * 4;

    /**
     * If the flag is true, then Stop searching after the first matches.
     */
    private final boolean flag;

    /**
     * Constructor.
     *
     * @param path       the root path from which the search begins.
     * @param findString text to find.
     * @param pattern    pattern for searching files.
     * @param flag       flag.
     */
    public Start(final Path path, final String findString, final Pattern pattern, final boolean flag) {
        this.path = path;
        this.findString = findString;
        this.pattern = pattern;
        this.flag = flag;
    }

    /**
     * Start search file.
     *
     * @throws InterruptedException exception.
     */
    public void startSearch() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);
        FindText find = new FindText(queue, findString);
        LOGGER.info("Start program!");
        LOGGER.info("Searching...");
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new FindPath(path, pattern, queue));
        thread.start();

        for (int i = 0; i < threadCount; i++) {
            service.execute(find);
        }

        if (flag) {
            while (!find.isStopFlag()) {
                thread.join(10);
            }
            thread.interrupt();
        }

        thread.join();
        Thread.sleep(3000);
        service.shutdownNow();
        LOGGER.info("Service shutdown!");

        long finish = System.currentTimeMillis();
        LOGGER.info(String.format("Time searching: %s", new SimpleDateFormat("mm:ss").format(finish - start)));
        LOGGER.info(String.format("Files left: %s", queue.size()));
        LOGGER.info("End program!");
    }

    /**
     * Main method.
     *
     * @param args args.
     * @throws InvalidKeyException throws if invalid key.
     */
    public static void main(String[] args) throws InvalidKeyException {
        final Pattern pattern = Pattern.compile("^*(.txt|.docx)$");
        final Validator validator = new Validator(args);

        if (validator.isCorrectKey()) {
            final Path path = validator.getPath();
            final String findText = validator.getFindText();
            final boolean flag = validator.isStopSearch();
            final Start start = new Start(path, findText, pattern, flag);
            try {
                start.startSearch();
            } catch (InterruptedException e) {
                LOGGER.error("Error: ", e);
            }
        } else {
            validator.showConditions();
            throw new InvalidKeyException("You entered not right key.");
        }
    }
}
