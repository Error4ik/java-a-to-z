package ru.job4j.lesson1.models;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

/**
 * Объект комментария.
 */
public class Comment {

    /**
     * Комментарий.
     */
    private String comment;

    /**
     * Дата создания комментария.
     */
    private Date createDate;

    /**
     * id Комментария.
     */
    private int id;

    /**
     * Если при создании объекта не указывается комментарий, то создается по дефолту с комментарием "Empty".
     */
    public Comment() {
        this("Empty");
    }

    /**
     * Конструктор.
     *
     * @param comment комментарий.
     */
    public Comment(String comment) {
        this.comment = comment;
        this.createDate = new Date();
        this.id = createdCommentId();
    }

    /**
     * Возвращает комментарий.
     *
     * @return комментарий.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Устанавливает комментарий.
     *
     * @param comment комментарий.
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Геттер для поля id.
     *
     * @return Возвращает id комментария.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод для сравнивая объектов комментария.
     *
     * @param o объект с которым сравнивается текущий.
     * @return возвращает true если объекты идентичны, в противном случае false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment = (Comment) o;
        return getId() == comment.getId()
                && Objects.equals(getComment(), comment.getComment());
    }

    /**
     * Метод hashCode.
     *
     * @return возвращает хеш код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getComment(), getId());
    }

    /**
     * Геттре для даты создания комментария.
     *
     * @return возвращает createDate.
     */
    protected Date getCreateDate() {
        return createDate;
    }

    /**
     * @return возвращает сгенерированный из даты создания комментария id.
     */
    private int createdCommentId() {
        final int d = 1000;
        final int randomNumber = (int) (new Random().nextInt(d) * 1.3);
        return (int) (getCreateDate().getTime() / d) / randomNumber;
    }

    @Override
    public String toString() {
        return "id: " + getId() + ": " + comment;
    }
}