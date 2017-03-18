package ru.job4j;

import java.util.Calendar;

/**
 * Hash User.
 *
 * @author Alexey Voronin.
 * @since 18.03.2017.
 */
public class UserHash extends User {

    /**
     * Constructor.
     *
     * @param name     user name.
     * @param children user children.
     * @param birthday user birthday.
     */
    public UserHash(String name, int children, Calendar birthday) {
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
        return false;
    }
}
