package ru.job4j.model;

import java.util.Date;
import java.util.Objects;

/**
 * User model.
 *
 * @author Alexey Voronin.
 * @since 06.07.2017.
 */
public class User {

    /**
     * User name.
     */
    private final String name;

    /**
     * User login.
     */
    private final String login;

    /**
     * User email.
     */
    private final String email;

    /**
     * Date of user creation.
     */
    private final Date createDate;

    /**
     * Constructor.
     *
     * @param name       user name.
     * @param login      user login.
     * @param email      user email.
     * @param createDate date of user creation.
     */
    public User(final String name, final String login, final String email, final Date createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    /**
     * Get.
     *
     * @return user name.
     */
    public String getName() {
        return this.name;
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
     * @return user email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Get.
     *
     * @return date of user creation.
     */
    public Date getCreateDate() {
        return this.createDate;
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
        return Objects.equals(getName(), user.getName())
                && Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getCreateDate(), user.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLogin(), getEmail(), getCreateDate());
    }

    @Override
    public String toString() {
        return String.format("User {name = %s, login = %s, email = %s, createDate = %s}",
                this.name, this.login, this.email, this.createDate);
    }
}
