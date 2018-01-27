package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * User role.
 *
 * @author Alexey Voronin.
 * @since 24.01.2018.
 */
@Entity(name = "roles")
public class Role {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Role.
     */
    private String role;

    /**
     * Users.
     */
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    /**
     * Default constructor.
     */
    public Role() {
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
     * @return role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Set.
     *
     * @param role role.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get.
     *
     * @return the list of users.
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Set.
     *
     * @param users users.
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        Role role1 = (Role) o;
        return getId() == role1.getId()
                && Objects.equals(getRole(), role1.getRole())
                && Objects.equals(getUsers(), role1.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }

    @Override
    public String toString() {
        return String.format("id=%s role=%s", getId(), getRole());
    }
}
