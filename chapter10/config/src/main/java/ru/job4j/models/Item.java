package ru.job4j.models;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Model.
 *
 * @author Alexey Voronin.
 * @since 25.10.2017.
 */
@Data
public class Item {
    private int id;
    private String description;
    private Timestamp created;
    private boolean done;
}
