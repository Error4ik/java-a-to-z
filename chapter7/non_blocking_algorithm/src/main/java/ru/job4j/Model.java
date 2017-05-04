package ru.job4j;

import java.util.Objects;
import java.util.UUID;

/**
 * Model.
 *
 * @author Alexey Voronin.
 * @since 04.05.2017.
 */
public class Model {

    /**
     * Name.
     */
    private String name;

    /**
     * Age.
     */
    private int age;

    /**
     * Version.
     */
    private int version;

    /**
     * UUID.
     */
    private UUID uuid;

    /**
     * Constructor.
     *
     * @param name name.
     * @param age  age.
     */
    public Model(final String name, final int age) {
        this.name = name;
        this.age = age;
        this.version = 1;
        this.uuid = UUID.randomUUID();
    }

    /**
     * Get.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
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
     * @return age.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Set.
     *
     * @param age age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get.
     *
     * @return version.
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Update version.
     */
    public void updateVersion() {
        this.version++;
    }

    /**
     * Get.
     *
     * @return UUID.
     */
    public UUID getUuid() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Model)) {
            return false;
        }
        Model model = (Model) o;
        return getAge() == model.getAge()
                && getVersion() == model.getVersion()
                && Objects.equals(getName(), model.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getVersion());
    }
}
