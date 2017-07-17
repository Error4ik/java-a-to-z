package ru.job4j.settings;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Settings from file.
 *
 * @author Alexey Voronin.
 * @since 06.07.2017.
 */
public class Settings {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(Settings.class);

    /**
     * Properties.
     */
    private final Properties properties;

    /**
     * Constructor.
     */
    public Settings() {
        this.properties = new Properties();
        this.load();
    }

    /**
     * Load settings from file.
     */
    private void load() {
        ClassLoader loader = this.getClass().getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            this.properties.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get value by key.
     *
     * @param key key.
     * @return value.
     */
    public String getValue(final String key) {
        return this.properties.getProperty(key);
    }
}
