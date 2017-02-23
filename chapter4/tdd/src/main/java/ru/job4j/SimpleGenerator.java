package ru.job4j;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Alexey Voronin.
 * @since 23.02.2017.
 */
public class SimpleGenerator implements Template {

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
     * This method parse string and replace values from map.
     *
     * @param string string.
     * @return modified string.
     */
    @Override
    public String generate(final String string) {
        StringTokenizer tokenizer = new StringTokenizer(string, " \t\n\r,:-?!", true);
        StringBuilder sb = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {
            String tmp = tokenizer.nextToken();
            if (this.getMap().containsKey(tmp)) {
                tmp = this.map.get(tmp);
                sb.append(tmp);
            } else {
                sb.append(tmp);
            }
        }
        return sb.toString();
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
