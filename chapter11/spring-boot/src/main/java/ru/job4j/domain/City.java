package ru.job4j.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * City.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "cities")
public class City {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * City name.
     */
    private String name;

    /**
     * Get.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set.
     *
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get.
     *
     * @return City name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set.
     *
     * @param name City name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        City city = (City) o;
        return getId() == city.getId()
                && Objects.equals(getName(), city.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return String.format("City {id=%s name=%s}", getId(), getName());
    }
}
