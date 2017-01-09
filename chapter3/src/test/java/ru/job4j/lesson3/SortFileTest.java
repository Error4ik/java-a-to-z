package ru.job4j.lesson3;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Тест класса SortFile.
 */
public class SortFileTest {

    /**
     * Тест метода sortByLongLines.
     */
    @Test
    public void sortByLongLinesTest() {
        SortFile sortFile = new SortFile();
        final File inFile = new File("..//pom.xml");
        final File outFile = new File("..//out.txt");
        try {
            sortFile.sortByLongLines(inFile, outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест бросает исключение если исходный фаил не существует.
     * @throws IOException ошибка ввода вывода.
     */
    @Test(expected = IOException.class)
    public void whenFirstFileDoesNotExistThenDropException() throws IOException {
        SortFile sortFile = new SortFile();
        final File inFile = new File("..//pomp.xml");
        final File outFile = new File("..//out.txt");
        sortFile.sortByLongLines(inFile, outFile);
    }

    /**
     * Тест бросает исключение если конечный фаил не указан.
     * @throws IOException ошибка ввода вывода.
     */
    @Test(expected = IOException.class)
    public void whenSecondFileDoesNotExistThenDropException() throws IOException {
        SortFile sortFile = new SortFile();
        final File inFile = new File("..//pom.xml");
        final File outFile = new File("");
        sortFile.sortByLongLines(inFile, outFile);
    }
}