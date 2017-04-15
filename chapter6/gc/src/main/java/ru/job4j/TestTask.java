package ru.job4j;

/**
 * TestTask.
 *
 * @author Alexey Voronin.
 * @since 14.04.2017.
 */
public class TestTask {

    /**
     * Expands the square array clockwise.
     *
     * @param inputArray input array.
     * @return Deployed array.
     */
    public int[] unwrap(final int[][] inputArray) {
        int size = inputArray.length;
        final int[] array = new int[size * size];
        int index = 0;
        for (int i = 0; i < (int) Math.ceil(inputArray.length / 2d); i++) {
            for (int j = 0; j < size; j++) {
                array[index++] = inputArray[i][i + j];
            }
            for (int j = 1; j < size; j++) {
                array[index++] = inputArray[i + j][inputArray.length - 1 - i];
            }
            for (int j = size - 2; j > -1; j--) {
                array[index++] = inputArray[inputArray.length - 1 - i][i + j];
            }
            for (int j = size - 2; j > 0; j--) {
                array[index++] = inputArray[i + j][i];
            }
            size -= 2;
        }
        return array;
    }
}
