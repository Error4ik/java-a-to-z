package ru.job4j;

import java.util.Calendar;
import java.util.Objects;

/**
 * Equals User.
 *
 * @author Alexey Voronin.
 * @since 18.03.2017.
 */
public class UserEquals extends User {

    /**
     * Constructor.
     *
     * @param name     user name.
     * @param children user children.
     * @param birthday user birthday.
     */
    public UserEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User that = (User) obj;
        return this.getChildren() == that.getChildren()
                && Objects.equals(this.getName(), that.getName())
                && Objects.equals(this.getBirthday(), that.getBirthday());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
