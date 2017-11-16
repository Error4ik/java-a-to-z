package ru.job4j.models;

import lombok.Data;

/**
 * Photo.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Data
public class Photo {
    private int id;
    private String name;
    private String imageUrl;
}
