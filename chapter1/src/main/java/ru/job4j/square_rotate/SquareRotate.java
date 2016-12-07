package ru.job4j.square_rotate;

/**
 * Класс для поврота двумерного массива на 90 градусов.
 */
public class SquareRotate {
    /**
     * Метод переворачивает олученный двумерный массив и возвращает его.
     * @param array массив который нужно перевернуть.
     * @return возвращает перевернутый на 90 градусов массив.
     */
    public int[][] squareRotate(int[][] array) {
        for (int i = 0; i < array.length / 2; i++) {
            for (int j = i; j < array.length - 1 - i; j++) {
                int temp = array[i][j];
                array[i][j] = array[j][array.length - 1 - i];
                array[j][array.length - 1 - i] = array[array.length - 1 - i][array.length - 1 - j];
                array[array.length - 1 - i][array.length - 1 - j] = array[array.length - 1 - j][i];
                array[array.length - 1 - j][i] = temp;
            }
        }
        return array;
    }
}