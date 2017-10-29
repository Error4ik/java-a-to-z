package ru.job4j.models;

import lombok.Data;

/**
 * Cat.
 *
 * @author Alexey Voronin.
 * @since 29.10.2017.
 */
@Data
public class Car {
    private int id;
    private String name;
    private CarBody carBody;
    private Engine engine;
    private Transmission transmission;
}
