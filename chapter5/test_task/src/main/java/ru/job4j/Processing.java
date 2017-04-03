package ru.job4j;

import ru.job4j.model.Book;
import ru.job4j.model.Order;
import ru.job4j.view.ConsoleView;

import java.util.HashMap;
import java.util.Map;

/**
 * Processing data.
 *
 * @author Alexey Voronin.
 * @since 31.03.2017.
 */
public class Processing {

    /**
     * Contains all books.
     */
    private Map<String, Book> bookMap = new HashMap<>();

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
     * Gets orders from map and distributes them to books.
     *
     * @param map map.
     */
    public void distributesOrders(final Map<Integer, Order> map) {
        for (Order order : map.values()) {
            String book = order.getBook();
            Book tmp = bookMap.get(book);
            if (tmp == null) {
                tmp = new Book();
                bookMap.put(book, tmp);
            } else {
                if ("BUY".equals(order.getOper())) {
                    this.fillTree(order, tmp.getBuy());
                } else {
                    this.fillTree(order, tmp.getSell());
                }
            }
        }

        for (Book books : bookMap.values()) {
            consoleView.show(books.getBuy(), books.getSell());
        }
    }

    /**
     * Method relates map and sell and fills the tree with orders.
     *
     * @param order order.
     * @param map   TreeMap.
     */
    private void fillTree(final Order order, final Map<Double, Order> map) {
        if (map.containsKey(order.getPrice())) {
            Order tmp = new Order(order.getBook(),
                    order.getOper(),
                    order.getPrice(),
                    order.getVolume() + map.get(order.getPrice()).getVolume(),
                    order.getOrderId());
            map.put(order.getPrice(), tmp);
        } else {
            map.put(order.getPrice(), order);
        }
    }
}
