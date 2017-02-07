package ru.job4j.additional_task;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс для поиска максимальной разницы одинаковых чисел в массиве.
 */
public class FindTheMaximumDifference {

    /**
     * Метод ищет самую большую разницу индексов одинаковых значений в массиве.
     *
     * @param source исходный массив.
     * @return максимальная разница индексов.
     */
    public int findNumber(final int[] source) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < source.length; i++) {
            for (int j = source.length - 1; j != i; j--) {
                if (source[i] == source[j]) {
                    list.add(Math.abs(j - i));
                }
            }
        }
        return Collections.max(list);
    }
}
