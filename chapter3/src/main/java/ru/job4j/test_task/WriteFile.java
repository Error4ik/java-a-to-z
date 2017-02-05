package ru.job4j.test_task;

import ru.job4j.test_task.settings.Settings;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс для записи в файл.
 */
public class WriteFile {

    /**
     * Класс с настройками.
     */
    private Settings settings = new Settings();

    /**
     * Метод записывает результат поиска в файл.
     * Если файл существует, то он удаляется и создается заного.
     *
     * @param outFile фаил в который нужно записать.
     * @param result  строка с данными для записи в файл.
     */
    public void writeResultToFile(final String outFile, final String result) {
        File outFileDir = new File(settings.getValue("log.file.directory"));
        outFileDir.mkdir();

        Path resultFile = Paths.get(outFileDir + System.getProperty("file.separator") + outFile);
        try {
            Files.deleteIfExists(resultFile);
            //Files.createFile(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream out = Files.newOutputStream(resultFile)) {
            out.write(result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
