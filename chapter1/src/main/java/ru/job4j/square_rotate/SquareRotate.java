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
        //для матрицы 3х3. для квадратных матриц большего размера алгоритм идентичный, просто увеличится число проходов.
        //при размере матрицы 3х3 внешний цикл сработает только 1 раз, а внутренний дважды.
        for (int i = 0; i < array.length / 2; i++) {
            for (int j = i; j < array.length - 1 - i; j++) {
                //сохраняем в переменную temp то что находится в ячейке 0 0 при первом проходе.
                //сохраняем в переменную temp то что находится в ячейке 0 1 при втором проходе.
                int temp = array[i][j];
                //записываем в ячейку 0 0 то что стоит в ячейке 0 2 при первом проходе.
                //записываем в ячейку 0 1 то что стоит в ячейке 1 2 при втором проходе.
                array[i][j] = array[j][array.length - 1 - i];
                //в ячейку 0 2 записываем то что стоит в ячейке 2 2 при первом проходе.
                //в ячейку 1 2 записываем то что стоит в ячейке 2 1 при втором проходе.
                array[j][array.length - 1 - i] = array[array.length - 1 - i][array.length - 1 - j];
                //в ячейку 2 2 записываем то что стоит в ячейке 2 0 при первом проходе.
                //в ячейку 2 1 записываем то что стоит в ячейке 1 0 при втором проходе.
                array[array.length - 1 - i][array.length - 1 - j] = array[array.length - 1 - j][i];
                //записываем в ячейку 2 0 то что было сохранено в переменную temp при первом проходе.
                //записываем в ячейку 1 0 то что было сохранено в переменной temp при втором проходе.
                array[array.length - 1 - j][i] = temp;
            }
        }
        return array;
    }
}