package ru.job4j.model;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Book.
 *
 * @author Alexey Voronin.
 * @since 04.04.2017.
 */
public class Book {

    /**
     * Comparator by ascending.
     */
    private final Comparator<Double> ascending = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o2.compareTo(o1);
        }
    };

    /**
     * Comparator by descending.
     */
    private final Comparator<Double> descending = new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * Buy orders.
     */
    private Map<Double, Order> buy = new TreeMap<>(descending);

    /**
     * Sell orders.
     */
    private Map<Double, Order> sell = new TreeMap<>(ascending);

    /**
     * Get.
     *
     * @return buy.
     */
    public Map<Double, Order> getBuy() {
        return this.buy;
    }

    /**
     * Get.
     *
     * @return sell.
     */
    public Map<Double, Order> getSell() {
        return this.sell;
    }
}
