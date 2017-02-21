package ru.job4j.simple_control_quality.food;

import java.time.LocalDate;

/**
 *
 */
public class Milk extends Foods {

    /**
     * Constructor.
     *
     * @param name       name.
     * @param createDate create date..
     * @param expireDate expire date.
     * @param price      price.
     */
    public Milk(String name, LocalDate createDate, LocalDate expireDate, double price) {
        super(name, createDate, expireDate, price);
    }
}
