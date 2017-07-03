package ru.job4j.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Vacancy.
 *
 * @author Alexey Voronin.
 * @since 21.06.2017.
 */
public class Vacancy {

    /**
     * The title of the vacancy.
     */
    private final String title;

    /**
     * Body of a vacancy.
     */
    private final String body;

    /**
     * Date of vacancy creation.
     */
    private final Date createDate;

    /**
     * Constructor.
     *
     * @param title      The title of the vacancy.
     * @param body       Body of a vacancy.
     * @param createDate Date of vacancy creation.
     */
    public Vacancy(final String title, final String body, final Date createDate) {
        this.title = title;
        this.body = body;
        this.createDate = createDate;
    }

    /**
     * Get.
     *
     * @return title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get.
     *
     * @return body.
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Get.
     *
     * @return create date.
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vacancy)) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(getTitle(), vacancy.getTitle())
                && Objects.equals(getBody(), vacancy.getBody())
                && Objects.equals(getCreateDate(), vacancy.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getBody(), getCreateDate());
    }

    @Override
    public String toString() {
        return String.format("Vacancy {title = %s, body = %s, create date = %s",
                title, body, new SimpleDateFormat("dd MMM yy HH:mm").format(this.createDate.getTime()));
    }
}
