package ru.job4j.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Advert.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "adverts")
public class Advert {

    /**
     * id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Title.
     */
    private String title;
    /**
     * Sale.
     */
    private Boolean sale;
    /**
     * Description.
     */
    private String description;
    /**
     * Date of created.
     */
    private Timestamp created;
    /**
     * Price.
     */
    private long price;
    /**
     * Mileage.
     */
    private Integer mileage;
    /**
     * Year of issue.
     */
    @Column(name = "year_of_issue")
    private Integer yearOfIssue;
    /**
     * Author.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;
    /**
     * City.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;
    /**
     * Photo.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "photo_id")
    private Image photo;
    /**
     * Car.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cars_id")
    private Car car;

    /**
     * Default constructor.
     */
    public Advert() {
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
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set.
     *
     * @param title title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get.
     *
     * @return description.
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

    /**
     * Get.
     *
     * @return sale.
     */
    public Boolean getSale() {
        return sale;
    }

    /**
     * Set.
     *
     * @param sale sale.
     */
    public void setSale(Boolean sale) {
        this.sale = sale;
    }

    /**
     * Get.
     *
     * @return author.
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Set author.
     *
     * @param author author.
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Get.
     *
     * @return date of created.
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Set.
     *
     * @param created date of created.
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Get.
     *
     * @return city.
     */
    public City getCity() {
        return city;
    }

    /**
     * Set.
     *
     * @param city city.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Get.
     *
     * @return price.
     */
    public long getPrice() {
        return price;
    }

    /**
     * Set.
     *
     * @param price price.
     */
    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * Get.
     *
     * @return photo.
     */
    public Image getPhoto() {
        return photo;
    }

    /**
     * Set.
     *
     * @param photo photo.
     */
    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    /**
     * Get.
     *
     * @return car.
     */
    public Car getCar() {
        return car;
    }

    /**
     * Set.
     *
     * @param car car.
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Get.
     *
     * @return yearOfIssue.
     */
    public Integer getYearOfIssue() {
        return yearOfIssue;
    }

    /**
     * Set.
     *
     * @param yearOfIssue yearOfIssue.
     */
    public void setYearOfIssue(Integer yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    /**
     * Get.
     *
     * @return mileage.
     */
    public Integer getMileage() {
        return mileage;
    }

    /**
     * Set.
     *
     * @param mileage mileage.
     */
    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Advert)) {
            return false;
        }
        Advert advert = (Advert) o;
        return getId() == advert.getId()
                && getPrice() == advert.getPrice()
                && Objects.equals(getTitle(), advert.getTitle())
                && Objects.equals(getDescription(), advert.getDescription())
                && Objects.equals(getSale(), advert.getSale())
                && Objects.equals(getAuthor(), advert.getAuthor())
                && Objects.equals(getCreated(), advert.getCreated())
                && Objects.equals(getCity(), advert.getCity())
                && Objects.equals(getPhoto(), advert.getPhoto())
                && Objects.equals(getCar(), advert.getCar())
                && Objects.equals(getYearOfIssue(), advert.getYearOfIssue())
                && Objects.equals(getMileage(), advert.getMileage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getSale(), getAuthor(), getCreated(), getCity(),
                getPrice(), getPhoto(), getCar(), getYearOfIssue(), getMileage());
    }

    @Override
    public String toString() {
        return String.format(
                "Advert {id=%s, title=%s, description=%s, sale=%s, created=%s, city=%s, price=%s, photo=%s, car=%s, "
                        + "yearOfIssue=%s, mileage=%s}",
                id, title, description, sale, created, city, price, photo, car, yearOfIssue, mileage);
    }
}
