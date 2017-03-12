package ru.job4j.store;

/**
 * Base.
 *
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public abstract class Base {

    /**
     * Id.
     */
    private String id;

    /**
     * Constructor.
     * @param id id.
     */
    public Base(final String id) {
        this.id = id;
    }

    /**
     * Get id.
     * @return id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set id.
     * @param id id.
     */
    public void setId(final String id) {
        this.id = id;
    }
}
