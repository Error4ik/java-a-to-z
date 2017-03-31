package ru.job4j.model;

import java.util.Objects;

/**
 * Order.
 *
 * @author Alexey Voronin.
 * @since 27.03.2017.
 */
public class Order {

    /**
     * Stock identifier.
     */
    private final String book;

    /**
     * Operation, buy or sell.
     */
    private final String oper;

    /**
     * Price.
     */
    private final double price;

    /**
     * Number of shares.
     */
    private final int volume;

    /**
     * Order id.
     */
    private final int orderId;

    /**
     * Constructor.
     *
     * @param book    Stock identifier.
     * @param oper    Operation, buy or sell.
     * @param price   price.
     * @param volume  Number of shares.
     * @param orderId Order id.
     */
    public Order(final String book, final String oper, final double price, final int volume, final int orderId) {
        this.book = book;
        this.oper = oper;
        this.price = price;
        this.volume = volume;
        this.orderId = orderId;
    }

    /**
     * Get.
     *
     * @return book.
     */
    public String getBook() {
        return book;
    }

    /**
     * Get.
     *
     * @return oper.
     */
    public String getOper() {
        return oper;
    }

    /**
     * Get.
     *
     * @return price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get.
     *
     * @return volume.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Get.
     *
     * @return orderId.
     */
    public int getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{"
                + "book='" + book + '\''
                + ", oper='" + oper + '\''
                + ", price=" + price
                + ", volume=" + volume
                + ", orderId=" + orderId
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return getOrderId() == order.getOrderId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId());
    }
}
