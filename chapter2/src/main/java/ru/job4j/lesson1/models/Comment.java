package ru.job4j.lesson1.models;

import java.util.Objects;

/**
 * Объект комментария.
 */
public class Comment {

    /**
     * Статическая переменная хранит количетсво созданных объектов Comment.
     */
    private static int countCreateComment;

    /**
     * Комментарий.
     */
    private String comment;

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
     * @param comment комментарий.
     */
    public Comment(String comment) {
        this.comment = comment;
        this.id = getCountCreateComment();
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

    /**
     * Геттер для поля id.
     * @return Возвращает id комментария.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод для сравнивая объектов комментария.
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
     * @return возвращает хеш код объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getComment(), getId());
    }

    /**
     * Геттер для статического поля.
     * @return возвращает количество созданных объектов типа Comment.
     */
    private static int getCountCreateComment() {
        return ++countCreateComment;
    }
}