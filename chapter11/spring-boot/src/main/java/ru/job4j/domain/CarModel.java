package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Car model.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "models")
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Model name.
     */
    private String name;

    /**
     * Brand a model.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;

    public CarModel() {
    }

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

    /**
     * Get.
     *
     * @return brand.
     */
    public CarBrand getCarBrand() {
        return carBrand;
    }

    /**
     * Set.
     *
     * @param carBrand brand.
     */
    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarModel)) {
            return false;
        }
        CarModel model = (CarModel) o;
        return getId() == model.getId()
                && Objects.equals(getName(), model.getName())
                && Objects.equals(getCarBrand(), model.getCarBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCarBrand());
    }

    @Override
    public String toString() {
        return String.format("CarModel: {id=%s name=%s brand=%s}", getId(), getName(), this.carBrand.toString());
    }
}
