package ru.job4j.merge_two_arrays;

/**
 * Класс для объеденения двух отсортированных массивов.
 */
class MergeTwoArrays {

    /**
     * Метод объеденяет 2 отсортированных массива.
     *
     * @param first  первый отсортированный массив.
     * @param second второй отсортированный массив.
     * @return возвращает отсортированный новый массив который содержит первый и второй массивы.
     */
    public int[] mergeTwoArrays(int[] first, int[] second) {
        int firstLen = first.length;
        int secondLen = second.length;

        final int[] resultArray = new int[firstLen + secondLen];

        int indexFirstArray = 0;
        int indexSecondArray = 0;
        int indexResultArray = 0;

        while (indexFirstArray < firstLen && indexSecondArray < secondLen) {
            if (first[indexFirstArray] < second[indexSecondArray]) {
                resultArray[indexResultArray] = first[indexFirstArray];
                indexFirstArray++;
            } else {
                resultArray[indexResultArray] = second[indexSecondArray];
                indexSecondArray++;
            }
            indexResultArray++;
        }

        while (indexFirstArray < firstLen) {
            resultArray[indexResultArray] = first[indexFirstArray];
            indexFirstArray++;
            indexResultArray++;
        }

        while (indexSecondArray < secondLen) {
            resultArray[indexResultArray] = second[indexSecondArray];
            indexSecondArray++;
            indexResultArray++;
        }
        return resultArray;
    }
}
