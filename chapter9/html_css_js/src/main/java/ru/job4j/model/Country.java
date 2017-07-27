package ru.job4j.model;

import java.util.Objects;

/**
 * Country.
 *
 * @author Alexey Voronin.
 * @since 23.07.2017.
 */
public class Country {

    /**
     * Country id.
     */
    private final int id;

    /**
     * Country name.
     */
    private final String country;

    /**
     * Constructor.
     *
     * @param id      id.
     * @param country country.
     */
    public Country(final int id, final String country) {
        this.id = id;
        this.country = country;
    }

    /**
     * Get.
     *
     * @return country id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get.
     *
     * @return country.
     */
    public String getCountry() {
        return this.country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        Country country1 = (Country) o;
        return getId() == country1.getId()
                && Objects.equals(getCountry(), country1.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountry());
    }

    @Override
    public String toString() {
        return String.format("Country: %s", this.country);
    }
}
