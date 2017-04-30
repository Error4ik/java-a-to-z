package ru.job4j.find_text;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileVisitResult;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bypasses the file system with the root, if the file
 * matches the pattern, it is added to the queue.
 *
 * @author Alexey Voronin.
 * @since 26.04.2017.
 */
public class FindPath implements Runnable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FindPath.class);

    /**
     * Root search directory.
     */
    private final Path rootDirectory;

    /**
     * Pattern for searching files.
     */
    private final Pattern pattern;

    /**
     * The queue adds file paths to it.
     */
    private final BlockingQueue<String> queue;

    /**
     * Matcher.
     */
    private Matcher matcher;

    /**
     * Constructor.
     *
     * @param rootDirectory root search directory.
     * @param pattern       pattern for searching files.
     * @param queue         the queue adds file paths to it.
     */
    public FindPath(final Path rootDirectory, final Pattern pattern, final BlockingQueue<String> queue) {
        this.rootDirectory = rootDirectory;
        this.pattern = pattern;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(this.rootDirectory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    matcher = pattern.matcher(String.valueOf(file.getFileName()));
                    if (matcher.find()) {
                        try {
                            queue.put(String.valueOf(file.toAbsolutePath()));
                        } catch (InterruptedException e) {
                            LOGGER.error("Interrupted: ", e);
                            return FileVisitResult.TERMINATE;
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            LOGGER.error("Error: ", e);
        }
    }
}
