package ru.job4j;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Cache test.
 *
 * @author Alexey Voronin.
 * @since 10.04.2017.
 */
public class CacheTest {

    /**
     * My Cache.
     */
    private final Cache cache = new Cache();

    /**
     * String separator.
     */
    private final String sep = System.getProperty("line.separator");

    /**
     * Method getFile.
     * If cache not contains file.
     */
    @Test
    public void whenCacheIsNotContainsFileShouldAddFileToCacheAndReturnFileContents() {
        final String expectedValue = String.format("111%s222%s333%s", sep, sep, sep);

        final String actualValue = cache.getFile(getClass().getResource("../../Test2.txt").getPath());

        assertThat(actualValue, is(expectedValue));
    }

    /**
     * Method getFile.
     * If cache contains file.
     */
    @Test
    public void whenTheCacheContainsAFileShouldReturnContentFromCache() {
        this.cache.getFile(getClass().getResource("../../Test1.txt").getPath());
        final String expectedValue = String.format("aaa%sbbb%sccc%s", sep, sep, sep);

        final String actualValue = this.cache.getFile(getClass().getResource("../../Test1.txt").getPath());

        assertThat(actualValue, is(expectedValue));
    }
}