package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * User.
 *
 * @author Alexey Voronin.
 * @since 30.10.2017.
 */
@Entity(name = "users")
public class User {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * Email.
     */
    private String email;
    /**
     * Password.
     */
    private String password;
    /**
     * List adverts.
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private List<Advert> adverts;

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
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set.
     *
     * @param email email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set.
     *
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get.
     *
     * @return the list of adverts.
     */
    public List<Advert> getAdverts() {
        return adverts;
    }

    /**
     * Set.
     *
     * @param adverts adverts.
     */
    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
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
                && Objects.equals(getEmail(), user.getEmail())
                && Objects.equals(getPassword(), user.getPassword())
                && Objects.equals(getAdverts(), user.getAdverts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getEmail(), getPassword(), getAdverts());
    }

    @Override
    public String toString() {
        return String.format("User {id=%s name=%s email=%s password=%s adverts=%s}",
                getId(), getName(), getEmail(), getPassword(), getAdverts());
    }
}
