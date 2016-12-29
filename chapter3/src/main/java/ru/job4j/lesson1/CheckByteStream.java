package ru.job4j.lesson1;

import java.io.BufferedReader;
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
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (Integer.parseInt(bufferedReader.readLine()) % 2 == 0) {
                result = true;
            }
        } catch (IOException e) {
            System.out.println("Error Input/Output: " + e);
        }
        return result;
    }
}