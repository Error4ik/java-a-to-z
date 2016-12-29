package ru.job4j.lesson1;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Класс для проверки байтового потока.
 */
public class CheckByteStream {

    /**
     * Метод проверяет является ли число из байтового потока четным.
     *
     * @param inputStream байтовый поток.
     * @return возвращает true если число четное и false если нет.
     */
    public boolean isNumber(final InputStream inputStream) {
        boolean result = false;
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader inputStr = new InputStreamReader(inputStream)) {
            int c;
            do {
                c = inputStr.read();
                if (c != -1) {
                    sb.append((char) c);
                }
            } while (c != -1);
        } catch (IOException e) {
            System.out.println("Error Input/Output: " + e);
        }

        if (Integer.parseInt(sb.toString()) % 2 == 0) {
            result = true;
        }
        return result;
    }
}