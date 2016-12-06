package ru.job4j.bubble;

/**
 * Класс дял пузырьковой сортировки массива.
 */
public class BubbleSort {

    /**
     * сортирует массив методом пузырьковой сортировки.
     * @param array массив который нужно отсортировать.
     * @return возвращает отсортированный массив.
     */
    public int[] bubbleSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = array.length - 1; j >= i; j--) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }
}