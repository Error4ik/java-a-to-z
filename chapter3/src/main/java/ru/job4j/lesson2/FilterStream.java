package ru.job4j.lesson2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Класс для удаления символов из потока.
 */
public class FilterStream {

    /**
     * Удаляет определенные слова из символьного потока.
     *
     * @param inputStream  входящий поток.
     * @param outputStream выходящий поток.
     * @param abuses       слова которые нужно удалить.
     */
    public void dropAbuses(final InputStream inputStream, final OutputStream outputStream, final String[] abuses) {
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    for (String abuse : abuses) {
                        if (line.contains(abuse)) {
                            line = line.replace(abuse, "").replace("  ", " ").trim();
                        }
                    }
                    outputStream.write(line.getBytes());
                }
            }
        } catch (IOException e) {
            System.out.println("Error I/O: " + e);
        }
    }
}
