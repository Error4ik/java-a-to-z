package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Drive unit car.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "drive_unit")
public class DriveUnit {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Drive unit name.
     */
    @Column(name = "drive_unit")
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
        if (!(o instanceof DriveUnit)) {
            return false;
        }
        DriveUnit unit = (DriveUnit) o;
        return getId() == unit.getId()
                && Objects.equals(getName(), unit.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return String.format("DriveUnit: {id=%s name=%s}", getId(), getName());
    }
}
