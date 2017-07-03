package ru.job4j;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Read file class.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class ReadFile {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ReadFile.class);

    /**
     * Returns the read file as a string.
     *
     * @param is input stream.
     * @return string.
     */
    public static String readFile(final InputStream is) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return sb.toString();
    }
}
