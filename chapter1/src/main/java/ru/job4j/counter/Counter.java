package ru.job4j.counter;

/**
 *  ласс вычисл¤т сумму четных чисел.
 */
public class Counter {

    /**
     * ћетод вычисл¤ет сумму всех четных чисел от start до end.
     * @param start первое число.
     * @param end последнее число.
     * @return возвращает сумму четных чисел.
     */
    public int add(final int start, final int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}