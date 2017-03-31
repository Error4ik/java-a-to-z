package ru.job4j;

import ru.job4j.model.Order;
import ru.job4j.view.ConsoleView;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Processing data.
 *
 * @author Alexey Voronin.
 * @since 31.03.2017.
 */
public class Processing {

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
     * Contains all orders for sale from book-1.
     */
    private Map<Double, Order> tree1Sell = new TreeMap<>(ascending);

    /**
     * Contains all orders for buy from book-1.
     */
    private Map<Double, Order> tree1Buy = new TreeMap<>(descending);

    /**
     * Contains all orders for sale from book-2.
     */
    private Map<Double, Order> tree2Sell = new TreeMap<>(ascending);

    /**
     * Contains all orders for buy from book-2.
     */
    private Map<Double, Order> tree2Buy = new TreeMap<>(descending);

    /**
     * Contains all orders for sale from book-3.
     */
    private Map<Double, Order> tree3Sell = new TreeMap<>(ascending);

    /**
     * Contains all orders for buy from book-3.
     */
    private Map<Double, Order> tree3Buy = new TreeMap<>(descending);

    /**
     * Output to console.
     */
    private ConsoleView consoleView;

    /**
     * Constructor.
     */
    public Processing() {
        this.consoleView = new ConsoleView();
    }

    /**
     * Gets orders from map and distributes them to trees.
     * @param map map.
     */
    public void distributesOrders(final Map<Integer, Order> map) {
        for (Order order : map.values()) {
            if (order.getBook().equals("book-1")) {
                this.fillTree(order, tree1Buy, tree1Sell);
            } else if (order.getBook().equals("book-2")) {
                this.fillTree(order, tree2Buy, tree2Sell);
            } else {
                this.fillTree(order, tree3Buy, tree3Sell);
            }
        }

        this.consoleView.show(this.tree1Buy, this.tree1Sell);
        this.consoleView.show(this.tree2Buy, this.tree2Sell);
        this.consoleView.show(this.tree3Buy, this.tree3Sell);
    }

    /**
     * Method relates buy and sell and fills the tree with orders.
     *
     * @param order order.
     * @param buy   HashMap to buy.
     * @param sell  HashMap to sell.
     */
    private void fillTree(final Order order, final Map<Double, Order> buy, Map<Double, Order> sell) {
        if (order.getOper().equals("BUY")) {
            if (buy.containsKey(order.getPrice())) {
                Order tmp = new Order(order.getBook(),
                        "BUY",
                        order.getPrice(),
                        order.getVolume() + buy.get(order.getPrice()).getVolume(),
                        order.getOrderId());
                buy.put(order.getPrice(), tmp);
            } else {
                buy.put(order.getPrice(), order);
            }
        } else {
            if (sell.containsKey(order.getPrice())) {
                Order tmp = new Order(order.getBook(),
                        "SELL",
                        order.getPrice(),
                        order.getVolume() + sell.get(order.getPrice()).getVolume(),
                        order.getOrderId());
                sell.put(order.getPrice(), tmp);
            } else {
                sell.put(order.getPrice(), order);
            }
        }
    }
}
