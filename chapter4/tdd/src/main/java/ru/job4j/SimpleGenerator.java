package ru.job4j;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ru.job4j.exception.NotFoundKeyException;

/**
 * @author Alexey Voronin.
 * @since 23.02.2017.
 */
public class SimpleGenerator implements Template {

    /**
     * Pattern regEx.
     */
    private static final Pattern PATTERN = Pattern.compile("\\$\\{(\\w+)\\}");

    /**
     * HashMap keeps patterns.
     */
    private final Map<String, String> map;

    /**
     * Constructor.
     * Init map, and added default templates.
     */
    public SimpleGenerator() {
        this.map = new HashMap<>();
    }

    /**
     * This method parse inputString and replace values from map.
     *
     * @param inputString inputString.
     * @return modified inputString.
     * @throws NotFoundKeyException not found key.
     */
    @Override
    public String generate(final String inputString) throws NotFoundKeyException {
        String result = "";
        Matcher matcher = PATTERN.matcher(inputString);
        while (matcher.find()) {
            if (this.map.containsKey(matcher.group(1))) {
                result = matcher.replaceFirst(this.map.get(matcher.group(1)));
                matcher.reset(result);
            } else {
                throw new NotFoundKeyException(String.format("Key not found %s", matcher.group(1)));
            }
        }
        return result;
    }

    /**
     * Add Template to the map.
     *
     * @param key   the key according to which you want to add the value.
     * @param value value that is added by a key.
     * @return true if value to added.
     */
    @Override
    public boolean addTemplateToTheMap(final String key, final String value) {
        this.map.put(key, value);
        return true;
    }

    /**
     * Remove template from map.
     *
     * @param key the key according to which you want to delete the value.
     * @return true if value to removed.
     */
    @Override
    public boolean removeTemplateFromMap(final String key) {
        boolean flag = false;
        if (this.getMap().containsKey(key)) {
            this.map.remove(key);
            flag = true;
        }
        return flag;
    }

    /**
     * Get.
     *
     * @return map.
     */
    @Override
    public Map<String, String> getMap() {
        return this.map;
    }
}
