package ru.job4j.merge_two_arrays;

/**
 * Класс для объеденения двух массивом.
 */
class MergeTwoArrays {

    /**
     * Метод объеденяет 2 массива и копирует с помощью циклов.
     *
     * @param one первый массив.
     * @param two второй массив.
     * @return Возвращает массив содержащий оба переданных.
     */
    int[] mergeTwoArrays(final int[] one, final int[] two) {
        int oneLength = one.length;
        int twoLength = two.length;
        final int[] array = new int[oneLength + twoLength];
        for (int i = 0; i < oneLength; i++) {
            array[i] = one[i];
        }

        int i = 0;
        for (int k = oneLength; k < array.length; k++) {
            array[k] = two[i++];
        }
        return array;
    }

    /**
     * Метод объеденяет 2 массива с помощью System.arraycopy.
     *
     * @param one первый массив.
     * @param two второй массив.
     * @return Возвращает массив содержащий оба переданных.
     */
    int[] mergeTwoArrays2(final int[] one, final int[] two) {
        int lengthOne = one.length;
        int lengthTwo = two.length;

        final int[] array = new int[lengthOne + lengthTwo];
        System.arraycopy(one, 0, array, 0, lengthOne);
        System.arraycopy(two, 0, array, lengthOne, lengthTwo);
        return array;
    }
}
