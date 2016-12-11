package ru.job4j.tracker.task;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Класс заявок.
 */
public class Task {

    /**
     * Размер массива Комментариев по умолчанию.
     */
    private static final int DEFAULT_SIZE_COMMENTS = 3;

    /**
     * Название заявки.
     */
    private String name;

    /**
     * Описание заявки.
     */
    private String description;

    /**
     * Дата создания заявки.
     */
    private Date createDate;

    /**
     * Комментарий к заявке.
     */
    private Comment[] comments;

    /**
     * Колличество коментариев в зявке.
     */
    private int countComment;

    /**
     * Идентификатор для заявки.
     */
    private long id;

    /**
     * Конструктор создает заявку.
     *
     * @param name        имя заявки.
     * @param description описание заявки.
     * @param id          идентификатор для заявки.
     */
    public Task(String name, String description, long id) {
        this.name = name;
        this.description = description;
        this.createDate = new Date();
        this.id = id;

        this.comments = new Comment[DEFAULT_SIZE_COMMENTS];
    }

    /**
     * Устанавливает комментарий к заявке.
     *
     * @param comment Комментарий который нужно установить.
     * @return Возвращает true если комментарий было добавлен, в противном случае false.
     */
    public boolean addComment(final Comment comment) {
        if (this.comments.length == countComment) {
            final Comment[] oldArray = this.comments;
            this.comments = new Comment[this.comments.length * 2];
            System.arraycopy(oldArray, 0, this.comments, 0, oldArray.length);
        }
        this.comments[this.countComment++] = comment;
        return true;
    }

    /**
     * Удаляет комментарий из массива.
     *
     * @param comment коментарий который нужно удалить.
     * @return возвращает true если элемент удалось удалить.
     */
    public boolean remove(final Comment comment) {
        boolean result = false;
        for (int i = 0; i < getCountComment(); i++) {
            if (this.comments[i].equals(comment)) {
                if (i != getCountComment() - 1) {
                    System.arraycopy(this.comments, i + 1, this.comments, i, getCountComment() - i - 1);
                }
                this.countComment--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Возвращает название заявки.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название заявки.
     * @param name название заявки.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает описание заявки.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание заявки.
     * @param description описание заявки.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Возвращает дату создания заявки.
     *
     * @return createDate.
     */
    protected Date getDate() {
        return this.createDate;
    }

    /**
     * Возвращает массив всех комментариев.
     *
     * @return массик.
     */
    public Comment[] getAllComments() {
        final Comment[] arrayComments = new Comment[this.countComment];
        System.arraycopy(this.comments, 0, arrayComments, 0, arrayComments.length);
        return arrayComments;
    }

    /**
     * Возвращает комментарий по индексу.
     *
     * @param index индекс комментария который нужно вернуть.
     * @return если индекс не валидный то возвращает пустой комментарий, если валидный возвращает комментарий по индексу.
     */
    public Comment getComment(final int index) {
        return index >= countComment ? new Comment() : this.comments[index];
    }

    /**
     * Указывает сколько комментариев в массиве.
     *
     * @return Возвращает количество коментариев в заявке.
     */
    public int getCountComment() {
        return this.countComment;
    }

    /**
     * Возвращает идентификатор заявки.
     *
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * Переопределенный метод для сравнивая объектов.
     *
     * @param o объект с которым сравнивается текущий.
     * @return возвращает true если объекты одинаковые, и false если нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return DEFAULT_SIZE_COMMENTS == task.DEFAULT_SIZE_COMMENTS
                &&  getCountComment() == task.getCountComment()
                && Objects.equals(getName(), task.getName())
                && Objects.equals(getDescription(), task.getDescription())
                && Objects.equals(getDate(), task.getDate())
                && Objects.equals(getId(), task.getId())
                && Arrays.equals(comments, task.comments);
    }

    /**
     * Переопределенный метод HashCod.
     *
     * @return возвращает хешкод объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(DEFAULT_SIZE_COMMENTS, getName(), getDescription(), getDate(), comments, getCountComment(), getId());
    }

    /**
     * Переопределенный метод toString.
     *
     * @return возвращает описание объекта.
     */
    @Override
    public String toString() {
        return "Task name: "
                + this.getName()
                + ", Task descriptions: "
                + this.getDescription()
                + ", Created Date: "
                + this.getDate()
                + ", Count comments: "
                + this.getCountComment()
                + ", id: " + this.getId()
                + System.getProperty("line.separator");
    }
}