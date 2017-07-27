package ru.job4j.model;

import java.util.Objects;

/**
 * City.
 *
 * @author Alexey Voronin.
 * @since 23.07.2017.
 */
public class City {

    /**
     * City id.
     */
    private final int id;

    /**
     * City name.
     */
    private final String city;

    /**
     * Country id.
     */
    private final int countryID;

    /**
     * Constructor.
     *
     * @param id        city id.
     * @param city      city name.
     * @param countryID country id.
     */
    public City(final int id, final String city, final int countryID) {
        this.id = id;
        this.city = city;
        this.countryID = countryID;
    }

    /**
     * Get.
     *
     * @return city id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get.
     *
     * @return city name.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Get.
     *
     * @return country id.
     */
    public int getCountryID() {
        return this.countryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        City city1 = (City) o;
        return getId() == city1.getId()
                && getCountryID() == city1.getCountryID()
                && Objects.equals(getCity(), city1.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getCountryID());
    }

    @Override
    public String toString() {
        return String.format("City: %s", this.city);
    }
}
