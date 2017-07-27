package ru.job4j.model;

import java.util.Date;
import java.util.Objects;


/**
 * User model.
 *
 * @author Alexey Voronin.
 * @since 08.07.2017.
 */
public class User {

    /**
     * User id.
     */
    private final int id;

    /**
     * User login.
     */
    private final String login;

    /**
     * User password.
     */
    private final String password;

    /**
     * User email.
     */
    private final String email;

    /**
     * Date of user creation.
     */
    private final Date createDate;

    /**
     * User role.
     */
    private final Role role;

    /**
     * User country.
     */
    private final Country country;

    /**
     * User city.
     */
    private final City city;

    /**
     * Constructor.
     *
     * @param id         user id.
     * @param login      user login.
     * @param password   user password.
     * @param email      user email.
     * @param createDate user create date.
     * @param role       user role.
     * @param country    user country.
     * @param city       user city.
     */
    public User(final int id, final String login, final String password, final String email,
                final Date createDate, final Role role, final Country country, final City city) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
        this.country = country;
        this.city = city;
    }

    /**
     * Get.
     *
     * @return user id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get.
     *
     * @return user login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Get.
     *
     * @return user password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get.
     *
     * @return user email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get.
     *
     * @return user create date.
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * Get.
     *
     * @return user role.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Get.
     *
     * @return country.
     */
    public Country getCountry() {
        return this.country;
    }

    /**
     * Get.
     *
     * @return city.
     */
    public City getCity() {
        return this.city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId()
                && Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getCreateDate(), user.getCreateDate())
                && Objects.equals(getRole(), user.getRole())
                && Objects.equals(getCountry(), user.getCountry())
                && Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(),
                getEmail(), getCreateDate(), getRole(), getCountry(), getCity());
    }

    @Override
    public String toString() {
        return String.format("User ID: %s, login: %s, password: %s, email: %s, createDate: %s, %s, %s, %s",
                this.id, this.login, this.password, this.email,
                this.createDate, this.role, this.country.getCountry(), this.city.getCity());
    }
}
