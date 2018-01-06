package ru.job4j.models;

import java.util.Objects;

/**
 * Common class for car parts.
 *
 * @author Alexey Voronin.
 * @since 12.11.2017.
 */
public class CarDetails {

    /**
     * Id.
     */
    private int id;
    /**
     * Detail name.
     */
    private String name;

    /**
     * Default constructor.
     */
    public CarDetails() {
    }

    /**
     * Get.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get.
     * @return detail name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set.
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarDetails)) {
            return false;
        }
        CarDetails that = (CarDetails) o;
        return getId() == that.getId()
                && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return String.format("%s {id=%s name=%s}", this.getClass().getSimpleName(), getId(), getName());
    }
}
