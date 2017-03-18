package ru.job4j;

import java.util.Calendar;

/**
 * Model User.
 *
 * @author Alexey Voronin.
 * @since 18.03.2017.
 */
public class User {

    /**
     * User name.
     */
    private final String name;

    /**
     * User children.
     */
    private final int children;

    /**
     * User birthday.
     */
    private final Calendar birthday;

    /**
     * Constructor.
     *
     * @param name     user name.
     * @param children user children.
     * @param birthday user birthday.
     */
    public User(final String name, final int children, final Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
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
     * @return children.
     */
    public int getChildren() {
        return this.children;
    }

    /**
     * Get.
     *
     * @return user birthday.
     */
    public Calendar getBirthday() {
        return this.birthday;
    }
}
