package ru.job4j;

import ru.job4j.exception.NotFoundKeyException;

import java.util.Map;

/**
 * @author Alexey Voronin.
 * @since 23.02.2017.
 */
public interface Template {

    /**
     * Template Hello ${name}.
     *
     * @param template template.
     * @return string.
     * @throws NotFoundKeyException exception.
     */
    String generate(final String template) throws NotFoundKeyException;

    /**
     * Add Template to the map.
     *
     * @param key   the key according to which you want to add the value.
     * @param value value that is added by a key.
     * @return true if value to added.
     */
    boolean addTemplateToTheMap(final String key, final String value);

    /**
     * Remove template from map.
     *
     * @param key the key according to which you want to delete the value.
     * @return true if value to removed.
     */
    boolean removeTemplateFromMap(final String key);

    /**
     * Get.
     *
     * @return map.
     */
    Map<String, String> getMap();
}
