package ru.job4j;

/**
 * User.
 *
 * @author Alexey Voronin.
 * @since 04.04.2017.
 */
public class User {

    /**
     * User name.
     */
    private String name;

    /**
     * Constructor.
     * @param name user name.
     */
    public User(final String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("-----Finalize-----");
    }

    /**
     * Get.
     * @return name.
     */
    public String getName() {
        return name;
    }
}
