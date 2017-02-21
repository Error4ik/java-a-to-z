package ru.job4j.extended_control_quality.food;

import java.time.LocalDate;

/**
 * Chocolate food.
 */
public class Chocolate implements IRecycleFood {

    /**
     * Name product.
     */
    private final String name;

    /**
     * Create date.
     */
    private final LocalDate createDate;

    /**
     * Date expire.
     */
    private final LocalDate expireDate;

    /**
     * Price product.
     */
    private final double price;

    /**
     * Discount price.
     */
    private int discount;

    /**
     * recycle flag.
     */
    private final boolean recycle;

    /**
     * Constructor.
     *
     * @param name       name.
     * @param createDate create date..
     * @param expireDate expire date.
     * @param price      price.
     */
    public Chocolate(final String name, final LocalDate createDate, final LocalDate expireDate, final double price) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.recycle = true;
    }

    /**
     * Get.
     *
     * @return recycle flag.
     */
    @Override
    public boolean isRecycle() {
        return this.recycle;
    }

    /**
     * Get.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get.
     *
     * @return create date.
     */
    public LocalDate getCreateDate() {
        return this.createDate;
    }

    /**
     * Get.
     *
     * @return expire date.
     */
    public LocalDate getExpireDate() {
        return this.expireDate;
    }

    /**
     * Get.
     *
     * @return price.
     */
    public double getPrice() {
        double result = 0;
        if (discount != 0) {
            result = this.price * this.discount / 100;
        }
        return Double.parseDouble(String.format("%.2f", this.price - result).replace(",", "."));
    }

    /**
     * Get.
     *
     * @return discount.
     */
    public int getDiscount() {
        return this.discount;
    }

    /**
     * Set discount.
     *
     * @param discount discount.
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * Method calculates the remaining shelf life.
     *
     * @return shelf life.
     */
    public double checkShelfLife() {
        int createDay = this.getCreateDate().getDayOfYear();
        int expireDay = this.getExpireDate().getDayOfYear();
        int currentDay = LocalDate.now().getDayOfYear();
        return Math.abs((double) (expireDay - currentDay) / (expireDay - createDay) * 100);
    }

    /**
     * toString.
     *
     * @return Description food.
     */
    @Override
    public String toString() {
        return String.format("{%s, createDate: %s, expireDate: %s, price: %s}",
                this.getName(), this.getCreateDate(), this.getExpireDate(), this.getPrice());
    }
}
