package ru.job4j.test_dly_seba.algorithms;

import java.util.Arrays;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 10.05.2019.
 */
public class RunClass {
    public static void main(String[] args) {
        // prepare.
        int[] unsortedArray = new int[200000];
        fillArray(unsortedArray);
//        printArray(unsortedArray);
        final SorterClass sorter = new SorterClass(unsortedArray);
        long start = 0;
        long end = 0;

        // bubble sort method.
        start = System.currentTimeMillis();
        int[] sort1 = sorter.bubbleSort();
        end = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Bubble sort time second - " + end);
        System.out.println("------------------------");

        // shaker sort method.
        start = System.currentTimeMillis();
        int[] sort2 = sorter.shakerSort();
        end = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Shaker sort time second - " + end);
        System.out.println("------------------------");

        // quick sort method.
        start = System.currentTimeMillis();
        final int[] result = new int[unsortedArray.length];
        System.arraycopy(unsortedArray, 0, result, 0, result.length);
        System.out.println("Quick sort - start");
        int[] sort3 = sorter.quickSort(result, 0, unsortedArray.length - 1);
        end = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Quick sort time second - " + end);
        System.out.println("------------------------");

        // insert sort
        start = System.currentTimeMillis();
        int[] sort4 = sorter.insertSort();
        end = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Insert sort time second - " + end);
        System.out.println("------------------------");

        // select sort
        start = System.currentTimeMillis();
        int[] sort5 = sorter.selectSort();
        end = (System.currentTimeMillis() - start) / 1000;
        System.out.println("Select sort time second - " + end);
        System.out.println("------------------------");

        System.out.println(Arrays.equals(sort1, sort2));
        System.out.println(Arrays.equals(sort3, sort4));
        System.out.println(Arrays.equals(sort1, sort4));
        System.out.println(Arrays.equals(sort2, sort5));
    }


    /**
     * Fill array method.
     *
     * @param array array.
     */
    private static void fillArray(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length);
        }
    }

    /**
     * Print array in the console.
     *
     * @param array array.
     */
    private static void printArray(final int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
