package ru.job4j.test_task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import ru.job4j.test_task.exception.InvalidKeyException;
import ru.job4j.test_task.settings.Settings;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Тест класса RunSearch.
 */
public class RunSearchTest {

    /**
     * Корневая дириктория.
     */
    private final String root = String.valueOf(Paths.get("/").toAbsolutePath());

    private String fileSep = System.getProperty("file.separator");

    /**
     * Перед началом тестов создается папка в корневой дириктории в которой создаются еще несколько подпапок,
     * и в них создаются файлы для проверки.
     *
     * @throws IOException ошибка ввода вывода.
     */
    @Before
    public void before() throws IOException {
        Files.createDirectories(Paths.get("/5TEST3/1/2"));
        Files.createFile(Paths.get("/5TEST3/aaa.txt"));
        Files.createFile(Paths.get("/5TEST3/1/aaa.txt"));
        Files.createFile(Paths.get("/5TEST3/1/2/bbb.txt"));
        Files.createFile(Paths.get("/5TEST3/1/2/555.docx"));
    }

    /**
     * После тестов удаляются файлы и папки созданные для теста.
     *
     * @throws IOException ошибка ввода вывода.
     */
    @After
    public void after() throws IOException {
        Files.deleteIfExists(Paths.get("/5TEST3/1/2/555.docx"));
        Files.deleteIfExists(Paths.get("/5TEST3/1/2/bbb.txt"));
        Files.deleteIfExists(Paths.get("/5TEST3/1/aaa.txt"));
        Files.deleteIfExists(Paths.get("/5TEST3/aaa.txt"));
        Files.deleteIfExists(Paths.get("/5TEST3/1/2"));
        Files.deleteIfExists(Paths.get("/5TEST3/1"));
        Files.deleteIfExists(Paths.get("/5TEST3"));
    }

    /**
     * Дириктория в которой осуществляется поиск.
     */
    private Path searchDirectory;

    /**
     * Метод проверят запись в файл результата поиска по маске.
     *
     * @throws Exception исключение.
     */
    @Test
    public void whenAFileIsFoundOnTheMaskThenWritesTheCorrectResultInTheFile() throws Exception {
        String[] array = {"-d", "/5TEST3/", "-n", "*.txt", "-m", "-o", "log.txt"};
        final String[] expected = {String.format("%s%s%s%s%s%s%s%s", root, "5TEST3", fileSep, 1, fileSep, 2, fileSep, "bbb.txt"),
                String.format("%s%s%s%s%s%s", root, "5TEST3", fileSep, 1, fileSep, "aaa.txt"),
                String.format("%s%s%s%s", root, "5TEST3", fileSep, "aaa.txt")};
        final String[] actual = new String[3];

        RunSearch runSearch = new RunSearch(array);
        runSearch.runSearch();

        final Path outFile = Paths.get(readFile("log.file.directory") + "/" + "log.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(
                String.valueOf(outFile.toAbsolutePath())))) {
            actual[0] = reader.readLine();
            actual[1] = reader.readLine();
            actual[2] = reader.readLine();
        }

        assertThat(actual, is(expected));
    }

    /**
     * Проверяет запись в файл результата поиска по полному имени файла.
     *
     * @throws Exception исключение.
     */
    @Test
    public void whenAFileIsFoundNamedThenWritesTheCorrectResultInTheFile() throws Exception {
        String[] array = {"-d", "/5TEST3/", "-n", "555.docx", "-f", "-o", "log.txt"};
        final String[] expected = {String.format("%s%s%s%s%s%s%s%s", root, "5TEST3", fileSep, 1, fileSep, 2, fileSep, "555.docx")};
        final String[] actual = new String[1];

        RunSearch runSearch = new RunSearch(array);
        runSearch.runSearch();

        final Path outFile = Paths.get(readFile("log.file.directory") + "/" + "log.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(
                String.valueOf(outFile.toAbsolutePath())))) {
            actual[0] = reader.readLine();
        }

        assertThat(actual, is(expected));
    }

    /**
     * @throws Exception исключение.
     */
    @Test(expected = InvalidKeyException.class)
    public void whenInvalidKeyThenThrowException() throws Exception {
        String[] array = {"-c", "/5TEST3/", "-n", "*.txt", "-m", "-o", "log.txt"};
        RunSearch search = new RunSearch(array);
        search.runSearch();
    }

    /**
     * Метод по ключу возвращает значение.
     *
     * @param key ключ для получения данных из файла.
     * @return Значеие по ключу.
     * @throws IOException ошибка ввода вывода.
     */
    private String readFile(final String key) throws IOException {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream inputStream = loader.getResourceAsStream("app.properties")) {
            settings.load(inputStream);
        }
        return settings.getValue(key);
    }
}