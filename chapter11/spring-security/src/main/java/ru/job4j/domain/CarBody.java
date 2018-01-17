package ru.job4j.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Car body.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "car_body")
public class CarBody {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * CarBody name.
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
     * @param id set id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get.
     *
     * @return car body name.
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
        if (!(o instanceof CarBody)) {
            return false;
        }
        CarBody body = (CarBody) o;
        return getId() == body.getId()
                && Objects.equals(getName(), body.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return String.format("CarBody: {id=%s name=%s}", getId(), getName());
    }
}
