package ru.job4j.simple_control_quality.food;

import java.time.LocalDate;

/**
 * Interface foods.
 */
public interface IFoods {

    /**
     * Get.
     *
     * @return name.
     */
    String getName();

    /**
     * Get.
     *
     * @return create date.
     */
    LocalDate getCreateDate();

    /**
     * Get.
     *
     * @return expire date.
     */
    LocalDate getExpireDate();

    /**
     * Get.
     *
     * @return price.
     */
    double getPrice();

    /**
     * Get.
     *
     * @return discount.
     */
    int getDiscount();

    /**
     * Set discount.
     *
     * @param discount discount.
     */
    void setDiscount(int discount);

    /**
     * Method calculates the remaining shelf life.
     *
     * @return shelf life.
     */
    double checkShelfLife();
}
