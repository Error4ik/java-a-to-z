package ru.job4j.test_dly_seba.algorithms;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 10.05.2019.
 */
public class SorterClass {

    private int[] array;

    private int length;

    public SorterClass(final int[] array) {
        this.array = array;
        this.length = array.length;
    }

    /**
     * Пузырьковая.
     *
     * @return sorted array.
     */
    public int[] bubbleSort() {
        System.out.println("Bubble sort - start");
        final int[] result = new int[length];
        System.arraycopy(array, 0, result, 0, length);
        for (int i = 1; i < length; i++) {
            for (int j = length - 1; j >= i; j--) {
                if (result[j - 1] > result[j]) {
                    int tmp = result[j - 1];
                    result[j - 1] = result[j];
                    result[j] = tmp;
                }
            }
        }
        return result;
    }

    /**
     * Шейкер.
     * Итерируясь находим в начале самый большой элемент, потом самый маленький,
     * Затем повторяе тоже самое исключая ранее найденные элементы.
     *
     * @return sorted array.
     */
    public int[] shakerSort() {
        System.out.println("Shaker sort - start");
        final int[] result = new int[length];
        System.arraycopy(array, 0, result, 0, length);
        int left = 1;
        int right = result.length - 1;

        do {
            for (int i = right; i >= left; i--) {
                if (result[i - 1] > result[i]) {
                    int tmp = result[i - 1];
                    result[i - 1] = result[i];
                    result[i] = tmp;
                }
            }
            left++;
            for (int i = left; i <= right; i++) {
                if (result[i - 1] > result[i]) {
                    int tmp = result[i - 1];
                    result[i - 1] = result[i];
                    result[i] = tmp;
                }
            }
            right--;
        } while (left <= right);
        return result;
    }

    /**
     * Быстрая сортировка.
     *
     * @param array Массив.
     * @param start начало массива.
     * @param end   конец массива.
     * @return отсортированный массив.
     */
    public int[] quickSort(final int[] array, final int start, final int end) {
        //останавливаем сортировку если начальный элемент равен или больше опорного.
        if (start >= end) {
            return array;
        }
        int i = start;
        int j = end;
        //Опорный элемент, всегда примерно середина массива.
        int op = i - (i - j) / 2;

        while (i < j) {
            //Двигаемся до первого элемента который больше опорного.
            while ((i < op) && (array[i] <= array[op])) {
                i++;
            }
            //Двигаемся до первого элемента который меньше опорного.
            while ((j > op) && (array[j] >= array[op])) {
                j--;
            }

            //Как только нашли элементы которые больше и меньше опорного, то меняем их местами. И сдвигаем опорный элемент.
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                if (i == op) {
                    op = j;
                } else if (j == op) {
                    op = i;
                }
            }
        }

        //рекурсивно обходим обе половины массива (отдельно до опорного элемента и после опорного).
        quickSort(array, start, op);
        quickSort(array, op + 1, end);

        return array;
    }

    /**
     * Сортировка методом вставки.
     *
     * @return отсортированный массив.
     */
    public int[] insertSort() {
        System.out.println("Insert sort - start");
        final int[] result = new int[length];
        System.arraycopy(array, 0, result, 0, length);

        int j;
        int tmp;
        for (int i = 0; i < result.length; i++) {
            //Сохраняем итый элемент для сравнения.
            tmp = result[i];
            //Если элемент tmp меньше чем j то меняем их и идем в обратном направлении сравнивая предыдущие элементы.
            for (j = i - 1; j >= 0 && result[j] > tmp; j--) {
                result[j + 1] = result[j];
            }
            result[j + 1] = tmp;
        }
        return result;
    }

    public int[] selectSort() {
        System.out.println("Insert sort - start");
        final int[] result = new int[length];
        System.arraycopy(array, 0, result, 0, length);

        int min = 0;
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            min = result[i];
            index = i;
            for (int j = i + 1; j < result.length; j++) {
                if (result[j] < min) {
                    index = j;
                    min = result[j];
                }
            }
            if (result[i] != result[index]) {
                result[index] = result[i];
                result[i] = min;
            }
        }

        return result;
    }
}
