package ru.job4j.models;

import lombok.Data;

/**
 * Car.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Data
public class Car {
    private int id;
    private CarDetails carBrand;
    private CarDetails carModel;
    private CarDetails carBody;
    private CarDetails transmission;
    private CarDetails engine;
    private CarDetails driverUnit;
}
