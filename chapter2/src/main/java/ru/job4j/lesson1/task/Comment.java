package ru.job4j.lesson1.task;

import java.util.Objects;

/**
 * Объект комментария.
 */
public class Comment {

    /**
     * Комментарий.
     */
    private String comment;

    /**
     * Если при создании объекта не указывается комментарий, то создается по дефолту с комментарием "Empty".
     */
    public Comment() {
        this("Empty");
    }

    /**
     * Конструктор.
     * @param comment комментарий.
     */
    public Comment(String comment) {
        this.comment = comment;
    }

    /**
     * Возвращает комментарий.
     * @return комментарий.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Устанавливает комментарий.
     * @param comment комментарий.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment1 = (Comment) o;
        return Objects.equals(getComment(), comment1.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComment());
    }
}