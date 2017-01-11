package ru.job4j.lesson4;

/**
 * Класс для проверки является ли слово палиндромом.
 */
public class Palindrome {

    /**
     * Метод проверяет является ли слово палиндромом.
     *
     * @param word слово которое нужно проверить.
     * @return возвращает true если является, и false если нет.
     */
    public boolean isPalindrome(final String word) {
        boolean result = true;
        char[] chars = word.toLowerCase().toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                result = false;
            }
        }
        return result;
    }
}