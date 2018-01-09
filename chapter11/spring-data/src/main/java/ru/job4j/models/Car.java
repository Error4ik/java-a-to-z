package ru.job4j.models;

import java.util.Objects;

/**
 * Car.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
public class Car {
    /**
     * Id.
     */
    private int id;
    /**
     * Brand.
     */
    private CarDetails carBrand;
    /**
     * Model.
     */
    private CarDetails carModel;
    /**
     * Car body.
     */
    private CarDetails carBody;
    /**
     * Transmission.
     */
    private CarDetails transmission;
    /**
     * Engine.
     */
    private CarDetails engine;
    /**
     * Drive unit.
     */
    private CarDetails driverUnit;

    /**
     * Default constructor.
     */
    public Car() {
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
     * @return carBrand.
     */
    public CarDetails getCarBrand() {
        return carBrand;
    }

    /**
     * Set.
     * @param carBrand carBrand.
     */
    public void setCarBrand(CarDetails carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * Get.
     * @return carModel.
     */
    public CarDetails getCarModel() {
        return carModel;
    }

    /**
     * Set.
     * @param carModel carModel.
     */
    public void setCarModel(CarDetails carModel) {
        this.carModel = carModel;
    }

    /**
     * Get.
     * @return carBody.
     */
    public CarDetails getCarBody() {
        return carBody;
    }

    /**
     * Set.
     * @param carBody carBody.
     */
    public void setCarBody(CarDetails carBody) {
        this.carBody = carBody;
    }

    /**
     * Get.
     * @return transmission.
     */
    public CarDetails getTransmission() {
        return transmission;
    }

    /**
     * Set.
     * @param transmission transmission.
     */
    public void setTransmission(CarDetails transmission) {
        this.transmission = transmission;
    }

    /**
     * Get.
     * @return engine.
     */
    public CarDetails getEngine() {
        return engine;
    }

    /**
     * Set.
     * @param engine engine.
     */
    public void setEngine(CarDetails engine) {
        this.engine = engine;
    }

    /**
     * Get.
     * @return driverUnit.
     */
    public CarDetails getDriverUnit() {
        return driverUnit;
    }

    /**
     * Set.
     * @param driverUnit driverUnit.
     */
    public void setDriverUnit(CarDetails driverUnit) {
        this.driverUnit = driverUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }
        Car car = (Car) o;
        return getId() == car.getId()
                && Objects.equals(getCarBrand(), car.getCarBrand())
                && Objects.equals(getCarModel(), car.getCarModel())
                && Objects.equals(getCarBody(), car.getCarBody())
                && Objects.equals(getTransmission(), car.getTransmission())
                && Objects.equals(getEngine(), car.getEngine())
                && Objects.equals(getDriverUnit(), car.getDriverUnit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCarBrand(), getCarModel(), getCarBody(), getTransmission(), getEngine(),
                getDriverUnit());
    }

    @Override
    public String toString() {
        return String.format("Car {id=%s brand=%s model=%s body=%s transmission=%s engine=%s unit=%s}",
                getId(), getCarBrand(), getCarModel(), getCarBody(), getTransmission(), getEngine(), getDriverUnit());
    }
}
