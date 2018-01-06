package ru.job4j.models;

/**
 * Car model.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */

public class CarModel extends CarDetails {

    private CarBrand carBrand;

    public CarModel() {
    }

    public CarBrand getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    @Override
    public String toString() {
        return String.format("{%s %s}", super.toString(), this.carBrand.toString());
    }
}
