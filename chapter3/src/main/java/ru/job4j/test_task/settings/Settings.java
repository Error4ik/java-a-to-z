package ru.job4j.test_task.settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс для загрузки свойств из файла.
 */
public class Settings {

    /**
     * Свойства.
     */
    private Properties properties;

    /**
     * Конструктор.
     */
    public Settings() {
        this.properties = new Properties();
        this.load();
    }

    /**
     * Метод загружает настройки из файла.
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
     * Метод возвращает значение по ключу.
     *
     * @param key ключ.
     * @return значение.
     */
    public String getValue(final String key) {
        return this.properties.getProperty(key);
    }
}
