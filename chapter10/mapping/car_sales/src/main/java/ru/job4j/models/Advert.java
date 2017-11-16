package ru.job4j.models;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Advert.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Data
public class Advert {
    private int id;
    private String title;
    private String description;
    private Boolean sale;
    private User author;
    private Timestamp created;
    private City city;
    private long price;
    private Photo photo;
    private Car car;
    private Integer yearOfIssue;
    private Integer mileage;

    @Override
    public String toString() {
        return String.format("Advert {id=%s, title=%s, description=%s, sale=%s, created=%s, city=%s, price=%s, photo=%s, car=%s, yearOfIssue=%s, mileage=%s}",
                        id, title, description, sale, created, city, price, photo, car, yearOfIssue, mileage);
    }
}
