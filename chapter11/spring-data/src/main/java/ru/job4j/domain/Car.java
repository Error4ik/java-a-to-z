package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Car.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "cars")
public class Car {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Brand.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brands_id")
    private CarBrand carBrand;
    /**
     * Model.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "models_id")
    private CarModel carModel;
    /**
     * Car body.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_body_id")
    private CarBody carBody;
    /**
     * Transmission.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gearboxes_id")
    private Transmission transmission;
    /**
     * Engine.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engines_id")
    private Engine engine;
    /**
     * Drive unit.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drive_unit_id")
    private DriveUnit driverUnit;

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
    public CarBrand getCarBrand() {
        return carBrand;
    }

    /**
     * Set.
     * @param carBrand carBrand.
     */
    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * Get.
     * @return carModel.
     */
    public CarModel getCarModel() {
        return carModel;
    }

    /**
     * Set.
     * @param carModel carModel.
     */
    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    /**
     * Get.
     * @return carBody.
     */
    public CarBody getCarBody() {
        return carBody;
    }

    /**
     * Set.
     * @param carBody carBody.
     */
    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    /**
     * Get.
     * @return transmission.
     */
    public Transmission getTransmission() {
        return transmission;
    }

    /**
     * Set.
     * @param transmission transmission.
     */
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Get.
     * @return engine.
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * Set.
     * @param engine engine.
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    /**
     * Get.
     * @return driverUnit.
     */
    public DriveUnit getDriverUnit() {
        return driverUnit;
    }

    /**
     * Set.
     * @param driverUnit driverUnit.
     */
    public void setDriverUnit(DriveUnit driverUnit) {
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
