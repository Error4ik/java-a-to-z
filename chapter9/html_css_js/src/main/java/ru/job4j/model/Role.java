package ru.job4j.model;

import java.util.Objects;

/**
 * Role.
 *
 * @author Alexey Voronin.
 * @since 14.07.2017.
 */
public class Role {

    /**
     * Role id.
     */
    private final int id;

    /**
     * Role.
     */
    private final String role;

    /**
     * Constructor.
     *
     * @param id   role id.
     * @param role role.
     */
    public Role(final int id, final String role) {
        this.id = id;
        this.role = role;
    }

    /**
     * Get.
     *
     * @return role id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get.
     *
     * @return role.
     */
    public String getRole() {
        return this.role;
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
                && Objects.equals(getRole(), role1.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRole());
    }

    @Override
    public String toString() {
        return String.format("role: %s", this.role);
    }
}
