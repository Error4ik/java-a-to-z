package ru.job4j.food;

import java.time.LocalDate;

/**
 * Meat foods.
 */
public class Meat extends Foods {
    /**
     * Constructor.
     *
     * @param name       name.
     * @param createDate create date..
     * @param expireDate expire date.
     * @param price      price.
     */
    public Meat(String name, LocalDate createDate, LocalDate expireDate, double price) {
        super(name, createDate, expireDate, price);
    }
}
