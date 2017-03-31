package ru.job4j;

import ru.job4j.settings.Settings;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * Start parser.
 *
 * @author Alexey Voronin.
 * @since 30.03.2017.
 */
public class Start {

    /**
     * Main method.
     *
     * @param args args.
     * @throws IOException i/o exception.
     */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        final Settings settings = new Settings();
        final ClassLoader loader = settings.getClass().getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            settings.load(in);
        }

        Parser parser = new Parser();
        Processing processing = new Processing();
        String file = settings.getValue("orders");

        try {
            processing.distributesOrders(parser.parse(file));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }

        System.out.printf("Time passed: %s second",
                new SimpleDateFormat("s").format(System.currentTimeMillis() - start));
    }
}
