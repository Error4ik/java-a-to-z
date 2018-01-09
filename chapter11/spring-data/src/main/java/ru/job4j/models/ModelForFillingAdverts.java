package ru.job4j.models;


/**
 * Model.
 *
 * @author Alexey Voronin.
 * @since 04.01.2018.
 */
public class ModelForFillingAdverts {
    /**
     * Title.
     */
    private String title;
    /**
     * Brand.
     */
    private String brand;
    /**
     * Model.
     */
    private String model;
    /**
     * Engine.
     */
    private String engine;
    /**
     * Transmission.
     */
    private String transmission;
    /**
     * Body.
     */
    private String body;
    /**
     * driver unit.
     */
    private String driveUnit;
    /**
     * Year of issue.
     */
    private String yearOfIssue;
    /**
     * Mileage.
     */
    private String mileage;
    /**
     * City.
     */
    private String city;
    /**
     * Price.
     */
    private String price;
    /**
     * Description.
     */
    private String description;

    /**
     * Get.
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set.
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get.
     *
     * @return brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set.
     *
     * @param brand brand.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get.
     *
     * @return model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Set.
     *
     * @param model model.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Get.
     *
     * @return engine.
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Set.
     *
     * @param engine engine.
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * Get.
     *
     * @return transmission.
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * Set.
     *
     * @param transmission transmission.
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * Get.
     *
     * @return body.
     */
    public String getBody() {
        return body;
    }

    /**
     * Set.
     *
     * @param body body.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Get.
     *
     * @return driver unit.
     */
    public String getDriveUnit() {
        return driveUnit;
    }

    /**
     * Set.
     *
     * @param driveUnit driver unit.
     */
    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }

    /**
     * Get.
     *
     * @return yearOfIssue.
     */
    public String getYearOfIssue() {
        return yearOfIssue;
    }

    /**
     * Set.
     *
     * @param yearOfIssue yearOfIssue.
     */
    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    /**
     * Get.
     *
     * @return mileage.
     */
    public String getMileage() {
        return mileage;
    }

    /**
     * Set.
     *
     * @param mileage mileage.
     */
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    /**
     * Get.
     *
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set.
     *
     * @param city city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get.
     *
     * @return price.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Set.
     *
     * @param price price.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Get.
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set.
     *
     * @param description description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "EmptyModelToAdvert{"
                + "title='" + title + '\''
                + ", brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", engine='" + engine + '\''
                + ", transmission='" + transmission + '\''
                + ", body='" + body + '\''
                + ", driveUnit='" + driveUnit + '\''
                + ", yearOfIssue='" + yearOfIssue + '\''
                + ", mileage='" + mileage + '\''
                + ", city='" + city + '\''
                + ", price='" + price + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
