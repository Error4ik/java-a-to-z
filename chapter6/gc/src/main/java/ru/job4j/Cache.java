package ru.job4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * My Cache.
 *
 * @author Alexey Voronin.
 * @since 09.04.2017.
 */
public class Cache {

    /**
     * Cache map.
     */
    private final Map<String, SoftReference<String>> cache = new HashMap<>();

    /**
     * String separator.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * If the data is in the cache then returns from the cache,
     * if not, then writes the data to the cache and returns.
     *
     * @param key file name.
     * @return file contents.
     */
    public String getFile(final String key) {
        StringBuilder sb = new StringBuilder();
        if (this.cache.containsKey(key)) {
            sb.append(this.cache.get(key).get());
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(key)))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(separator);
                }
                this.cache.put(key, new SoftReference<String>(sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
