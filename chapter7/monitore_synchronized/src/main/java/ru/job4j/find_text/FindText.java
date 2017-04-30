package ru.job4j.find_text;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.BlockingQueue;

/**
 * It takes the path to the file from the queue,
 * reads the file and looks for a match with the specified string.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class FindText implements Runnable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FindText.class);

    /**
     * The path to the files is taken from the queue.
     */
    private final BlockingQueue<String> queue;

    /**
     * Text to find.
     */
    private final String findText;

    /**
     * Stop flag.
     */
    private boolean stopFlag = false;

    /**
     * Constructor.
     *
     * @param queue    the path to the files is taken from the queue.
     * @param findText text to find.
     */
    public FindText(final BlockingQueue<String> queue, final String findText) {
        this.queue = queue;
        this.findText = findText;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String path = queue.take();
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(path), StandardCharsets.UTF_8))) {
                    String word;
                    while ((word = reader.readLine()) != null) {
                        if (word.contains(findText)) {
                            LOGGER.info(String.format("Match found: %s", path));
                            this.stopFlag = true;
                            break;
                        }
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            LOGGER.error("Interrupter: ", e);
        }
    }

    /**
     * Get.
     * @return flag.
     */
    public boolean isStopFlag() {
        return this.stopFlag;
    }
}
