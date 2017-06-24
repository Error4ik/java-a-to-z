package ru.job4j;

import java.util.Date;
import java.util.Objects;

/**
 * TODO: comment.
 *
 * @author Alexey Voronin.
 * @since 21.06.2017.
 */
public class Vacancy {

    private final String title;

    private final String url;

    private final Date createDate;

    public Vacancy(final String title, final String url, final Date createDate) {
        this.title = title;
        this.url = url;
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(getTitle(), vacancy.getTitle()) &&
                Objects.equals(getUrl(), vacancy.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getUrl());
    }

    @Override
    public String toString() {
        return "Vacancy {" +
                "title = '" + title + '\'' +
                ", url = '" + url + '\'' +
                ", create date + " + createDate + '}';
    }
}
