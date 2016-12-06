package ru.job4j.max;

/**
 * Клас для нахождения максимального числа.
 */
public class Max {

    /**
     * Метод получает на вход 2 числа и возвращает то которое больше.
     * @param first первое число.
     * @param second второе число.
     * @return возвращает большее число.
     */
    public int findMaxTwoNumber(final int first, final int second) {
        return first > second ? first : second;
    }

    /**
     * Находит большее из трех чисел.
     * @param first первое число.
     * @param second второе число.
     * @param third третье число.
     * @return возвращает большее из трех чисел.
     */
    public int findLargeNumberOfThree(final int first, final int second, final int third) {
        return findMaxTwoNumber(findMaxTwoNumber(first, second), third);
    }
}