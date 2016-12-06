package ru.job4j.turn;

/**
 * Класс для переворота массива.
 */
public class Turn {

    /**
     * Метод переворачивает массив чисел.
     * @param array Массив который нужно перевернуть.
     * @return возвращает перевернутый массив
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int firstElement = array[i];
            int lastElement = array[array.length - 1 - i];
            array[i] = lastElement;
            array[array.length - 1 - i] = firstElement;
        }
        return array;
    }
}