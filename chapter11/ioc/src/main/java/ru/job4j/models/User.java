package ru.job4j.models;

import java.util.Objects;

/**
 * User model.
 *
 * @author Alexey Voronin.
 * @since 04.12.2017.
 */

public class User {

    /**
     * User id.
     */
    private int id;

    /**
     * User name.
     */
    private String name;

    /**
     * User login.
     */
    private String login;

    /**
     * User password.
     */
    private String password;

    /**
     * Default constructor.
     */
    public User() {

    }

    /**
     * Get.
     *
     * @return user id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set.
     *
     * @param id user id.
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Get.
     *
     * @return user name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set.
     *
     * @param name user name.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Get.
     *
     * @return user login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set.
     *
     * @param login user login.
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Get.
     *
     * @return user password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set.
     *
     * @param password user password.
     */
    public void setPassword(final String password) {
        this.password = password;
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
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getLogin(), user.getLogin())
                && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogin(), getPassword());
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
