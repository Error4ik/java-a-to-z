package ru.job4j.remove_duplicate;

import java.util.Arrays;

/**
 * Класс для удаления дубликатов.
 */
public class RemoveDuplicate {

    /**
     * Метод удаляет из массива дубликаты.
     * @param strings массив из которого нужно удалить дубликаты.
     * @return возвращает копию оригинального массива без пустых ячеек.
     */
    public String[] removeDuplicate(String[] strings) {
        int count = 0;
        for (int i = 0; i < strings.length; i++) {
            count = getCount(strings, count, i);
        }
        return Arrays.copyOf(strings, strings.length - count);
    }

    /**
     * Метод проверяет содержатся ли в массиве повторяющиеся значения.
     * Если находит повторяющиеся значение то записывет в эту ячейку null.
     * @param strings массив в котором ищутся дубликаты.
     * @param count переменная для подсчета найденных дубликатов.
     * @param i переменная счетчика для сравнивания элементов.
     * @return возвращает количество дубликатов.
     */
    private int getCount(String[] strings, int count, int i) {
        for (int j = i + 1; j < strings.length; j++) {
            if (strings[i] != null && strings[i].equals(strings[j])) {
                strings[j] = null;
                count++;
            }
        }
        return count;
    }
}