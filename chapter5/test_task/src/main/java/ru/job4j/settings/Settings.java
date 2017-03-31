package ru.job4j.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Settings.
 *
 * @author Alexey Voronin.
 * @since 30.03.2017.
 */
public class Settings {

    /**
     * Properties.
     */
    private Properties properties;

    /**
     * Constructor.
     */
    public Settings() {
        this.properties = new Properties();
    }

    /**
     * Load setting from file.
     *
     * @param inputStream stream.
     */
    public void load(final InputStream inputStream) {
        try {
            this.properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
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
