package ru.job4j.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Car brand.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "brands")
public class CarBrand {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Car brand name.
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
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set.
     *
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
        if (!(o instanceof CarBrand)) {
            return false;
        }
        CarBrand brand = (CarBrand) o;
        return getId() == brand.getId()
                && Objects.equals(getName(), brand.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return String.format("CarBrand: {id=%s name=%s}", getId(), getName());
    }
}
