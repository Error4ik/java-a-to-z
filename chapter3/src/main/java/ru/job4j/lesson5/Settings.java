package ru.job4j.lesson5;

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
    private Properties properties = new Properties();

    /**
     * Метод загружает настройки из файла.
     * @param io поток.
     */
    public void load(final InputStream io) {
        try {
            this.properties.load(io);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает значение по ключу.
     * @param key ключ.
     * @return значение.
     */
    public String getValue(final String key) {
        return this.properties.getProperty(key);
    }
}
