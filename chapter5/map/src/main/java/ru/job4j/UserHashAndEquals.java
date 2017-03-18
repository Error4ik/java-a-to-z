package ru.job4j;

import java.util.Calendar;
import java.util.Objects;

/**
 * HashCode and equals.
 *
 * @author Alexey Voronin.
 * @since 18.03.2017.
 */
public class UserHashAndEquals extends User {

    /**
     * Constructor.
     *
     * @param name     user name.
     * @param children user children.
     * @param birthday user birthday.
     */
    public UserHashAndEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        int hash = this.getName() != null ? this.getName().hashCode() : 0;
        hash = 31 * hash + this.getChildren();
        hash = 31 * hash + (this.getBirthday() != null ? this.getBirthday().hashCode() : 0);
        return hash;
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
}
