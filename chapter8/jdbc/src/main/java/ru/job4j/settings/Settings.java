package ru.job4j.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Settings from file.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
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
        this.load();
    }

    /**
     * Load settings from file.
     */
    private void load() {
        ClassLoader loader = this.getClass().getClassLoader();
        try (InputStream is = loader.getResourceAsStream("app.properties")) {
            this.properties.load(is);
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
