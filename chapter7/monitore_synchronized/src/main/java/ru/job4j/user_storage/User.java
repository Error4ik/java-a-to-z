package ru.job4j.user_storage;

import java.util.Objects;
import java.util.UUID;

/**
 * User.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class User {

    /**
     * User id.
     */
    private final UUID uuid;

    /**
     * User name.
     */
    private final String name;

    /**
     * User money.
     */
    private double balance;

    /**
     * Constructor.
     *
     * @param name user name.
     */
    public User(final String name) {
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.balance = 0d;
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
     * Get.
     *
     * @return Amount of money the user.
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Set.
     *
     * @param balance balance.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Get.
     *
     * @return user uuid.
     */
    public UUID getUuid() {
        return this.uuid;
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
        return Objects.equals(getUuid(), user.getUuid())
                && Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getName());
    }

    @Override
    public String toString() {
        return String.format("User: %s, balance: %s", this.getName(), this.getBalance());
    }
}
