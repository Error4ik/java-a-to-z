package ru.job4j.test_task;

import java.util.Arrays;

/**
 * Проверяет содержится ли подскрока в строке.
 */
class TestTask {

    /**
     * Метод проверяет содержится ли подстрока в строке.
     * @param string строка в которой нужно наййти подстроку.
     * @param sub подстрака которую нужно найти.
     * @return возвращает true если подстрока находится в строке, в противном случае false.
     */
    boolean isContains(String string, String sub) {
        final char[] originString = string.toLowerCase().toCharArray();
        final char[] subString = sub.toLowerCase().toCharArray();
        boolean result = false;

        for (int i = 0; i < originString.length; i++) {
            if (originString.length - i < subString.length) {
                break;
            }

            if (originString[i] == subString[0]) {
                final char[] verificationArray = new char[subString.length];
                System.arraycopy(originString, i, verificationArray, 0, verificationArray.length);
                result = Arrays.equals(subString, verificationArray);
            }

            if (result) {
                break;
            }
        }
        return result;
    }
}
