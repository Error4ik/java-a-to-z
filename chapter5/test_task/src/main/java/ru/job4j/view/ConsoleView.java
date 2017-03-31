package ru.job4j.view;

import ru.job4j.model.Order;

import java.util.Map;

/**
 * Output to console.
 *
 * @author Alexey Voronin.
 * @since 31.03.2017.
 */
public class ConsoleView {

    /**
     * Displays all books in the console.
     *
     * @param sell sell.
     * @param buy  buy.
     */
    public void show(Map<Double, Order> buy, Map<Double, Order> sell) {
        StringBuilder sb = new StringBuilder();
        System.out.println("BUY   PRICE  PRICE    SELL");
        for (Order order : sell.values()) {
            sb.append(String.format("\t\t\t %5s %7s\n", order.getPrice(), order.getVolume()));
        }
        for (Order order : buy.values()) {
            sb.append(String.format("%7s %5s\n", order.getVolume(), order.getPrice()));
        }
        System.out.println(sb);
    }

}
